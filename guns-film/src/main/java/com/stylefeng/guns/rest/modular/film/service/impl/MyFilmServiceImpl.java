package com.stylefeng.guns.rest.modular.film.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import com.stylefeng.guns.rest.film.FilmService;
import com.stylefeng.guns.rest.film.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Service(interfaceClass = FilmService.class)
public class MyFilmServiceImpl implements FilmService {

    @Autowired
    private MtimeBannerTMapper bannerTMapper;

    @Autowired
    private MtimeFilmTMapper filmTMapper;

    @Autowired
    private MtimeCatDictTMapper catDictTMapper;

    @Autowired
    private MtimeSourceDictTMapper sourceDictTMapper;

    @Autowired
    private MtimeYearDictTMapper yearDictTMapper;

    @Autowired
    private MtimeFilmInfoTMapper filmInfoTMapper;

    @Autowired
    private MtimeActorTMapper actorTMapper;

    @Override
    public List<BannerVO> getBanners() {
        List<BannerVO> result = new ArrayList<>();
        List<MtimeBannerT> bannerTList = bannerTMapper.selectList(null);
        for (MtimeBannerT bannerT : bannerTList) {
            BannerVO bannerVO = new BannerVO();
            bannerVO.setBannerId(""+bannerT.getUuid());
            bannerVO.setBannerAddress(bannerT.getBannerAddress());
            bannerVO.setBannerUrl(bannerT.getBannerUrl());
            result.add(bannerVO);
        }
        return result;
    }

    private List<FilmInfo> getFilmInfos(List<MtimeFilmT> filmTList){
        List<FilmInfo> filmInfos = new ArrayList<>();
        for (MtimeFilmT mtimeFilmT : filmTList) {
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setScore(mtimeFilmT.getFilmScore());
            filmInfo.setImgAddress(mtimeFilmT.getImgAddress());
            filmInfo.setFilmType(mtimeFilmT.getFilmType());
            filmInfo.setFilmScore(mtimeFilmT.getFilmScore());
            filmInfo.setFilmName(mtimeFilmT.getFilmName());
            filmInfo.setFilmId(""+mtimeFilmT.getUuid());
            filmInfo.setExpectNum(mtimeFilmT.getFilmPresalenum());
            filmInfo.setBoxNum(mtimeFilmT.getFilmBoxOffice());
            filmInfo.setShowTime(DateUtil.getDay(mtimeFilmT.getFilmTime()));
            //将转换的对象放入结果集
            filmInfos.add(filmInfo);
        }
        return filmInfos;
    }

    @Override
    public FilmVO getHotFilms(boolean isLimit, int nums) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();

        //正在热映影片的限制条件
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");
        //判断是否是首页需要的内容
        if (isLimit){
            //如果是，则限制条数，限制内容为热映影片
            Page<MtimeFilmT> page = new Page<>(1,nums);
            List<MtimeFilmT> filmTList = filmTMapper.selectPage(page, entityWrapper);
            //组织filmInfo
            filmInfos = getFilmInfos(filmTList);
            filmVO.setFilmNum(filmTList.size());
            filmVO.setFilmInfo(filmInfos);
        }else {
            //如果不是，则是列表页，同样限制内容是热映影片

        }

        return filmVO;
    }

    @Override
    public FilmVO getSoonFilms(boolean isLimit, int nums) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();

        //即将上映影片的限制条件
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");
        //判断是否是首页需要的内容
        if (isLimit){
            //如果是，则限制条数，限制内容为热映影片
            Page<MtimeFilmT> page = new Page<>(1,nums);
            List<MtimeFilmT> filmTList = filmTMapper.selectPage(page, entityWrapper);
            //组织filmInfo
            filmInfos = getFilmInfos(filmTList);
            filmVO.setFilmNum(filmTList.size());
            filmVO.setFilmInfo(filmInfos);
        }else {
            //如果不是，则是列表页，同样限制内容是热映影片

        }

        return filmVO;
    }

    @Override
    public List<FilmInfo> getBoxRanking() {
        //条件   -> 正在上映的，票房前10名

        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");

        Page<MtimeFilmT> page = new Page<>(1,10,"film_box_office");

        List<MtimeFilmT> filmTList = filmTMapper.selectPage(page, entityWrapper);

        List<FilmInfo> filmInfos = getFilmInfos(filmTList);
        return filmInfos;
    }

    @Override
    public List<FilmInfo> getExpectRanking() {
        //条件   -> 即将上映的，预售前10名

        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","2");

        Page<MtimeFilmT> page = new Page<>(1,10,"film_preSaleNum");

        List<MtimeFilmT> filmTList = filmTMapper.selectPage(page, entityWrapper);

        List<FilmInfo> filmInfos = getFilmInfos(filmTList);
        return filmInfos;
    }

    @Override
    public List<FilmInfo> getTop() {
        //条件   -> 正在上映的，评分前10名
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");

        Page<MtimeFilmT> page = new Page<>(1,10,"film_score");

        List<MtimeFilmT> filmTList = filmTMapper.selectPage(page, entityWrapper);

        List<FilmInfo> filmInfos = getFilmInfos(filmTList);
        return filmInfos;
    }

    @Override
    public List<CatVO> getCats() {
        List<CatVO> cats = new ArrayList<>();

        //查询实体对象 - MtimeCatDictT
        List<MtimeCatDictT> catDictTList = catDictTMapper.selectList(null);
        //将实体对象转换为业务对象 - CatVO
        for (MtimeCatDictT catDictT : catDictTList) {
            CatVO catVO = new CatVO();
            catVO.setCatId(""+catDictT.getUuid());
            catVO.setCatName(catDictT.getShowName());

            cats.add(catVO);
        }
        return cats;
    }

    @Override
    public List<SourceVO> getSources() {
        List<SourceVO> sources = new ArrayList<>();
        List<MtimeSourceDictT> sourceDictTList = sourceDictTMapper.selectList(null);
        for (MtimeSourceDictT sourceDictT : sourceDictTList) {
            SourceVO sourceVO = new SourceVO();
            sourceVO.setSourceId(""+sourceDictT.getUuid());
            sourceVO.setSourceName(sourceDictT.getShowName());

            sources.add(sourceVO);
        }
        return sources;
    }

    @Override
    public List<YearVO> getYears() {
        List<YearVO> years = new ArrayList<>();
        List<MtimeYearDictT> yearDictTList = yearDictTMapper.selectList(null);
        for (MtimeYearDictT yearDictT : yearDictTList) {
            YearVO yearVO = new YearVO();
            yearVO.setYearId(""+yearDictT.getUuid());
            yearVO.setYearName(yearDictT.getShowName());

            years.add(yearVO);
        }
        return years;
    }

    @Override
    public FilmVO getHotFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();

        //正在热映影片的限制条件
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");
        //判断是否是首页需要的内容
        if (isLimit){
            //如果是，则限制条数，限制内容为热映影片
            Page<MtimeFilmT> page = new Page<>(1,nums);
            List<MtimeFilmT> filmTList = filmTMapper.selectPage(page, entityWrapper);
            //组织filmInfo
            filmInfos = getFilmInfos(filmTList);
            filmVO.setFilmNum(filmTList.size());
            filmVO.setFilmInfo(filmInfos);
        }else {
            //如果不是，则是列表页，同样限制内容是热映影片
            Page<MtimeFilmT> page = null;
            //根据sortId的不同，来组织不同的Page对象
            // 排序方式，1-按热门搜索，2-按时间搜索，3-按评价搜索
            switch (sortId){
                case 1:
                    page = new Page<>(nowPage,nums,"film_box_office");//根据film_box_office字段降序
                    break;
                case 2:
                    page = new Page<>(nowPage,nums,"film_time");
                    break;
                case 3:
                    page = new Page<>(nowPage,nums,"film_score");
                    break;
                default:
                    page = new Page<>(nowPage,nums,"film_box_office");//默认根据film_box_office字段降序
                    break;
            }
            //如果sourceId，yearId，catId不为99，则表示按照对应的编号进行查询
            if (sourceId!=99){
                entityWrapper.eq("film_source", sourceId);
            }
            if (yearId!=99){
                entityWrapper.eq("film_date", yearId);
            }
            if (catId!=99){
                // 数据库存的数据：#2#4#22，需要模糊查询
                String catStr = "%#"+catId+"#%";
                entityWrapper.like("film_cats", catStr);
            }
            List<MtimeFilmT> filmTList = filmTMapper.selectPage(page, entityWrapper);
            //组织filmInfo
            filmInfos = getFilmInfos(filmTList);
            filmVO.setFilmNum(filmTList.size());
            filmVO.setFilmInfo(filmInfos);

            //需要总页数
            Integer totalCount = filmTMapper.selectCount(entityWrapper);
            int totalPages = (totalCount/(nums+1))+1;

            filmVO.setNowPage(nowPage);
            filmVO.setTotalPage(totalPages);
        }
        return filmVO;
    }

    @Override
    public FilmVO getSoonFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();

        //即将上映影片的限制条件
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","2");
        //判断是否是首页需要的内容
        if (isLimit){
            //如果是，则限制条数，限制内容为即将上映
            Page<MtimeFilmT> page = new Page<>(1,nums);
            List<MtimeFilmT> filmTList = filmTMapper.selectPage(page, entityWrapper);
            //组织filmInfo
            filmInfos = getFilmInfos(filmTList);
            filmVO.setFilmNum(filmTList.size());
            filmVO.setFilmInfo(filmInfos);
        }else {
            //如果不是，则是列表页，同样限制内容是热映影片
            Page<MtimeFilmT> page = null;
            //根据sortId的不同，来组织不同的Page对象
            // 排序方式，1-按热门搜索，2-按时间搜索，3-按评价搜索
            switch (sortId){
                case 1:
                    page = new Page<>(nowPage,nums,"film_preSaleNum");//未上映电影根据预售量来判断热度
                    break;
                case 2:
                    page = new Page<>(nowPage,nums,"film_time");
                    break;
                case 3:
                    page = new Page<>(nowPage,nums,"film_preSaleNum");
                    break;
                default:
                    page = new Page<>(nowPage,nums,"film_preSaleNum");//默认根据film_preSaleNum字段降序
                    break;
            }
            //如果sourceId，yearId，catId不为99，则表示按照对应的编号进行查询
            if (sourceId!=99){
                entityWrapper.eq("film_source", sourceId);
            }
            if (yearId!=99){
                entityWrapper.eq("film_date", yearId);
            }
            if (catId!=99){
                // 数据库存的数据：#2#4#22，需要模糊查询
                String catStr = "%#"+catId+"#%";
                entityWrapper.like("film_cats", catStr);
            }
            List<MtimeFilmT> filmTList = filmTMapper.selectPage(page, entityWrapper);
            //组织filmInfo
            filmInfos = getFilmInfos(filmTList);
            filmVO.setFilmNum(filmTList.size());
            filmVO.setFilmInfo(filmInfos);

            //需要总页数
            Integer totalCount = filmTMapper.selectCount(entityWrapper);
            int totalPages = (totalCount/(nums+1))+1;

            filmVO.setNowPage(nowPage);
            filmVO.setTotalPage(totalPages);
        }
        return filmVO;
    }

    @Override
    public FilmVO getClassicFilms(int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();

        //经典影片的限制条件
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","3");

        Page<MtimeFilmT> page = null;
        //根据sortId的不同，来组织不同的Page对象
        // 排序方式，1-按热门搜索，2-按时间搜索，3-按评价搜索
        switch (sortId){
            case 1:
                page = new Page<>(nowPage,nums,"film_box_office");//根据film_box_office字段降序
                break;
            case 2:
                page = new Page<>(nowPage,nums,"film_time");
                break;
            case 3:
                page = new Page<>(nowPage,nums,"film_score");
                break;
            default:
                page = new Page<>(nowPage,nums,"film_box_office");//默认根据film_box_office字段降序
                break;
        }

        //如果sourceId，yearId，catId不为99，则表示按照对应的编号进行查询
        if (sourceId!=99){
            entityWrapper.eq("film_source", sourceId);
        }
        if (yearId!=99){
            entityWrapper.eq("film_date", yearId);
        }
        if (catId!=99){
            // 数据库存的数据：#2#4#22，需要模糊查询
            String catStr = "%#"+catId+"#%";
            entityWrapper.like("film_cats", catStr);
        }
        List<MtimeFilmT> filmTList = filmTMapper.selectPage(page, entityWrapper);
        //组织filmInfo
        filmInfos = getFilmInfos(filmTList);
        filmVO.setFilmNum(filmTList.size());
        filmVO.setFilmInfo(filmInfos);

        //需要总页数
        Integer totalCount = filmTMapper.selectCount(entityWrapper);
        int totalPages = (totalCount/(nums+1))+1;

        filmVO.setNowPage(nowPage);
        filmVO.setTotalPage(totalPages);
        return filmVO;

    }

    @Override
    public FilmDetailVO getFilmDetail(int searchType, String searchParam) {
        FilmDetailVO filmDetailVO = new FilmDetailVO();
        //searchType 1-按名称 0-按编号
        if (searchType==1){
            filmDetailVO = filmTMapper.getFilmDetailByName(searchParam);
        }else {
            filmDetailVO = filmTMapper.getFilmDetailById(searchParam);
        }

        return filmDetailVO;
    }

    private MtimeFilmInfoT getFilmInfo(String filmId){
        MtimeFilmInfoT filmInfoT = new MtimeFilmInfoT();
        filmInfoT.setFilmId(filmId);
        filmInfoT = filmInfoTMapper.selectOne(filmInfoT);
        return filmInfoT;
    }

    @Override
    public FilmDescVO getFilmDesc(String filmId) {
        MtimeFilmInfoT filmInfo = getFilmInfo(filmId);

        FilmDescVO filmDescVO = new FilmDescVO();
        filmDescVO.setBiography(filmInfo.getBiography());
        filmDescVO.setFilmId(filmId);

        return filmDescVO;
    }

    @Override
    public ImgVO getImgs(String filmId) {
        MtimeFilmInfoT filmInfo = getFilmInfo(filmId);
        //图片地址是五个以逗号为分隔的链接URL
        String filmImgStr = filmInfo.getFilmImgs();
        String[] filmImgs = filmImgStr.split(",");

        ImgVO imgVO = new ImgVO();
        imgVO.setMainImg(filmImgs[0]);
        imgVO.setImg01(filmImgs[1]);
        imgVO.setImg02(filmImgs[2]);
        imgVO.setImg03(filmImgs[3]);
        imgVO.setImg04(filmImgs[4]);

        return imgVO;
    }

    @Override
    public ActorVO getDectInfo(String filmId) {
        MtimeFilmInfoT filmInfo = getFilmInfo(filmId);

        //获取导演编号
        Integer directorId = filmInfo.getDirectorId();
        MtimeActorT actorT = actorTMapper.selectById(directorId);

        ActorVO actorVO = new ActorVO();
        actorVO.setDirectorName(actorT.getActorName());
        actorVO.setImgAddress(actorT.getActorImg());
        return actorVO;
    }

    @Override
    public List<ActorVO> getActors(String filmId) {
        List<ActorVO> actors = actorTMapper.getActors(filmId);

        return actors;
    }
}
