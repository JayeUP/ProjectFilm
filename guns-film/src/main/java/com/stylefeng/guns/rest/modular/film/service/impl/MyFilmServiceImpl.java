package com.stylefeng.guns.rest.modular.film.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.film.FilmService;
import com.stylefeng.guns.rest.film.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = FilmService.class)
public class MyFilmServiceImpl implements FilmService {

    @Autowired
    MtimeBannerTMapper bannerMapper;

    @Autowired
    MtimeFilmTMapper filmMapper;

    @Autowired
    MtimeCatDictTMapper catMapper;

    @Autowired
    MtimeSourceDictTMapper sourceMapper;

    @Autowired
    MtimeYearDictTMapper yearMapper;

    @Override
    public String test() {
        return "test zookeeper-dubbo";
    }

    @Override
    public List<BannerVO> getBanners() {
        List<BannerVO> banners= bannerMapper.getBanners();
        return banners;
    }

    @Override
    public FilmVO getHotFilms() {
        //score>8为hotFilm
        int hotFilmsNumnum=filmMapper.getHotFilmsNum();
        List<FilmInfo> hotFilms=filmMapper.getHotFilms();
        FilmVO filmVO = new FilmVO();
        filmVO.setFilmNum(hotFilmsNumnum);
        filmVO.setFilmInfo(hotFilms);
        return filmVO;
    }

    @Override
    public FilmVO getSoonFilms() {
        //查询上映日大于当前日期的影片
        int soonFilmsNum =filmMapper.getSoonFilmsNum();
        List<FilmInfo> soonFilms =filmMapper.getSoonFilms();
        FilmVO filmVO = new FilmVO();
        filmVO.setFilmNum(soonFilmsNum);
        filmVO.setFilmInfo(soonFilms);
        return filmVO;
    }

    @Override
    public List<FilmInfo> getBoxRanking() {
        List<FilmInfo> boxRanking= filmMapper.getBoxRanking();
        return boxRanking;
    }

    @Override
    public List<FilmInfo> getExpectRanking() {
        List<FilmInfo> expectRanking= filmMapper.getExpectRanking();
        return expectRanking;
    }

    @Override
    public List<FilmInfo> getTop100() {
        List<FilmInfo> top100= filmMapper.getTop100();
        return top100;
    }

    @Override
    public List<CatVO> getCats() {
        List<CatVO> cats = catMapper.getCats();
        return cats;
    }

    @Override
    public List<SourceVO> getSources() {
        List<SourceVO> sources = sourceMapper.getSources();
        return sources;
    }

    @Override
    public List<YearVO> getYears() {
        List<YearVO> years = yearMapper.getYears();
        return years;
    }

    @Override
    public FilmVO getHotFilms(int nowPage, int pageSize, int sortId, int catId, int sourceId, int yearId) {
        return null;
    }

    @Override
    public FilmVO getSoonFilms(int nowPage, int pageSize, int sortId, int catId, int sourceId, int yearId) {
        return null;
    }

    @Override
    public FilmVO getClassicFilms(int nowPage, int pageSize, int sortId, int catId, int sourceId, int yearId) {
        return null;
    }
}
