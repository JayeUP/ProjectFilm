package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.film.FilmService;
import com.stylefeng.guns.rest.film.vo.CatVO;
import com.stylefeng.guns.rest.film.vo.SourceVO;
import com.stylefeng.guns.rest.film.vo.YearVO;
import com.stylefeng.guns.rest.vo.FilmConditionVO;
import com.stylefeng.guns.rest.vo.FilmIndexVO;
import com.stylefeng.guns.rest.vo.FilmRequestVO;
import com.stylefeng.guns.rest.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @RequestMapping("/test")
    public String getResult(){
        System.out.println("get rest!");
        return "ok";
    }

    @Reference
    FilmService filmService;

    @RequestMapping("/testFilm")
    public String testFilm(){
        return filmService.test();
    }

    //首页请求
    @RequestMapping(value = "/getIndex",method = RequestMethod.GET)
    public ResponseVO getIndex(){


        FilmIndexVO filmIndexVO = new FilmIndexVO();
        filmIndexVO.setBanners(filmService.getBanners());
        filmIndexVO.setHotFilms(filmService.getHotFilms());
        filmIndexVO.setSoonFilms(filmService.getSoonFilms());
        filmIndexVO.setBoxRanking(filmService.getBoxRanking());
        filmIndexVO.setExpectRanking(filmService.getExpectRanking());
        filmIndexVO.setTop100(filmService.getTop100());

        ResponseVO<Object> responseVO = new ResponseVO<>();
        responseVO.setStatus(0);
        responseVO.setImgPre("http://img.meetingshop.cn/");
        responseVO.setData(filmIndexVO);
        return responseVO;
    }

    //影片条件列表查询请求
    @RequestMapping(value = "/getConditionList",method = RequestMethod.GET)
    public ResponseVO getConditionList(@RequestParam(value = "catId",required = false,defaultValue = "99")String catId,
                                   @RequestParam(value = "sourceId",required = false,defaultValue = "99")String sourceId,
                                   @RequestParam(value = "yearId",required = false,defaultValue = "99")String yearId){
        List<CatVO> cats = filmService.getCats();
        for (CatVO cat : cats) {
            //查找参数catId与cat中的id相等时，则将其Active置为true。
            //可优化 通过二分查找（有序的情况）
            if (catId.equals(cat.getCatId())){
                cat.setActive(true);
            }else{
                //默认为false，可以不设置
                cat.setActive(false);
            }
        }
        List<SourceVO> sources = filmService.getSources();
        for (SourceVO source : sources) {
            if (sourceId.equals(source.getSourceId())){
                source.setActive(true);
            }else {
                source.setActive(false);
            }
        }
        List<YearVO> years = filmService.getYears();
        for (YearVO year : years) {
            if (yearId.equals(year.getYearId())){
                year.setActive(true);
            }else {
                year.setActive(false);
            }
        }
        FilmConditionVO filmConditionVO = new FilmConditionVO();
        filmConditionVO.setCats(cats);
        filmConditionVO.setSources(sources);
        filmConditionVO.setYears(years);

        ResponseVO<Object> responseVO = new ResponseVO<>();
        responseVO.setStatus(0);
        responseVO.setData(filmConditionVO);
        return responseVO;
    }

    @RequestMapping(value = "/getFilm", method = RequestMethod.GET)
    public ResponseVO getFilms(FilmRequestVO filmRequestVO) {
        switch (filmRequestVO.getShowType()) {
            case 1:

        }
        return new ResponseVO();
    }
}
