package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.film.FilmService;

import com.stylefeng.guns.rest.film.vo.*;

import com.stylefeng.guns.rest.modular.film.vo.FilmConditionVO;
import com.stylefeng.guns.rest.modular.film.vo.FilmIndexVO;

import com.stylefeng.guns.rest.modular.film.vo.FilmRequestVO;
import com.stylefeng.guns.rest.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film/")
public class FilmController {

    private static final String IMG_PRE = "http://img.meetingshop.cn/";

    @Reference(interfaceClass = FilmService.class,check = false)
    private FilmService filmServiceApi;

    /*
        API网关：
            1、功能聚合【API聚合】
            好处：
                1、六个接口，一次请求，同一时刻节省了五次HTTP请求
                2、同一个接口对外暴露，降低了前后端分离开发的难度和复杂度
            坏处：
                1、一次获取数据过多，容易出现问题
     */
    @RequestMapping(value = "getIndex",method = RequestMethod.GET)
    public ResponseVO getIndex(){

        FilmIndexVO filmIndexVO = new FilmIndexVO();

        //获取bannner信息
        filmIndexVO.setBanners(filmServiceApi.getBanners());
        //获取正在热映的电影
        filmIndexVO.setHotFilms(filmServiceApi.getHotFilms(true, 8));
        //即将上映的电影
        filmIndexVO.setSoonFilms(filmServiceApi.getSoonFilms(true, 8));
        //票房排行榜
        filmIndexVO.setBoxRanking(filmServiceApi.getBoxRanking());
        //获取受欢迎的榜单
        filmIndexVO.setExpectRanking(filmServiceApi.getExpectRanking());
        //获取前一百
        filmIndexVO.setTop100(filmServiceApi.getTop());

        return ResponseVO.success(IMG_PRE,filmIndexVO);
    }

    @RequestMapping(value = "getConditionList",method = RequestMethod.GET)
    public ResponseVO getConditionList(@RequestParam(value = "catId",required = false,defaultValue = "99")String catId,
                                       @RequestParam(value = "sourceId",required = false,defaultValue = "99")String sourceId,
                                       @RequestParam(value = "yearId",required = false,defaultValue = "99")String yearId){

        FilmConditionVO filmConditionVO = new FilmConditionVO();
        //类型集合
        List<CatVO> cats = filmServiceApi.getCats();
        for (CatVO cat : cats) {
            //判断集合是否存在catId，如果存在，则将对应的实体变为active状态
            //可优化 通过二分查找（有序的情况）
            if (catId.equals(cat.getCatId())){
                cat.setActive(true);
            }
            //其他的active状态均默认为false
        }
        //片源集合
        List<SourceVO> sources = filmServiceApi.getSources();
        for (SourceVO source : sources) {
            if (sourceId.equals(source.getSourceId())){
                source.setActive(true);
            }
        }
        //年代集合
        List<YearVO> years = filmServiceApi.getYears();
        for (YearVO year : years) {
            if (yearId.equals(year.getYearId())){
                year.setActive(true);
            }
        }
        filmConditionVO.setCats(cats);
        filmConditionVO.setSources(sources);
        filmConditionVO.setYears(years);

        return ResponseVO.success(filmConditionVO);
    }

    @RequestMapping(value = "getFilms",method = RequestMethod.GET)
    public ResponseVO getFilms(FilmRequestVO filmRequestVO){
        FilmVO filmVO = null;
        //根据showType判断影片查询类型
        switch (filmRequestVO.getShowType()){
            case 1:
                filmVO = filmServiceApi.getHotFilms(
                        false,
                        filmRequestVO.getPageSize(), filmRequestVO.getNowPage(), filmRequestVO.getSortId(),
                        filmRequestVO.getSourceId(), filmRequestVO.getYearId(), filmRequestVO.getCatId());
                break;
            case 2:
                filmVO = filmServiceApi.getSoonFilms(
                        false,
                        filmRequestVO.getPageSize(), filmRequestVO.getNowPage(), filmRequestVO.getSortId(),
                        filmRequestVO.getSourceId(), filmRequestVO.getYearId(), filmRequestVO.getCatId());
                break;
            case 3:
                filmVO = filmServiceApi.getClassicFilms(
                        filmRequestVO.getPageSize(), filmRequestVO.getNowPage(), filmRequestVO.getSortId(),
                        filmRequestVO.getSourceId(), filmRequestVO.getYearId(), filmRequestVO.getCatId());
                break;
            default:
                filmVO = filmServiceApi.getHotFilms(
                        false,
                        filmRequestVO.getPageSize(), filmRequestVO.getNowPage(), filmRequestVO.getSortId(),
                        filmRequestVO.getSourceId(), filmRequestVO.getYearId(), filmRequestVO.getCatId());
                break;
        }

        return ResponseVO.success(
                filmVO.getNowPage(),filmVO.getTotalPage(),
                IMG_PRE,filmVO.getFilmInfo());
    }

    @RequestMapping(value = "/films/{searchParam}",method = RequestMethod.GET)
    public ResponseVO films(@PathVariable("searchParam") String searchParam, int searchType){
        //根据searchType，判断查询类型
        FilmDetailVO filmDetail = filmServiceApi.getFilmDetail(searchType, searchParam);

        String filmId = filmDetail.getFilmId();
        //查询影片的详细信息 -> Dubbo的异步获取
        //获取图片信息
        ImgVO imgs = filmServiceApi.getImgs(filmId);
        //获取影片详细描述信息
        FilmDescVO filmDesc = filmServiceApi.getFilmDesc(filmId);
        //获取导演信息
        ActorVO director = filmServiceApi.getDectInfo(filmId);
        //获取演员信息
        List<ActorVO> actors = filmServiceApi.getActors(filmId);

        ActorRequestVO actorRequestVO = new ActorRequestVO();
        actorRequestVO.setDirector(director);
        actorRequestVO.setActors(actors);

        InfoRequestVO infoRequestVO = new InfoRequestVO();
        infoRequestVO.setBiography(filmDesc.getBiography());
        infoRequestVO.setActors(actorRequestVO);

        filmDetail.setInfo04(infoRequestVO);
        filmDetail.setImgs(imgs);

        return ResponseVO.success(IMG_PRE, filmDetail);
    }
}
