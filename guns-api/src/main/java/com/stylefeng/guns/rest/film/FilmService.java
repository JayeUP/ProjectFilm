package com.stylefeng.guns.rest.film;

import com.stylefeng.guns.rest.film.vo.*;

import java.util.List;

public interface FilmService {
    //测试
    String test();

    /*
        首页接口
    */
    //获取banners
    List<BannerVO> getBanners();
    //获取热门影片
    FilmVO getHotFilms();
    //获取即将上映的影片
    FilmVO getSoonFilms();
    //获取票房排名
    List<FilmInfo> getBoxRanking();
    //获取人气排名
    List<FilmInfo> getExpectRanking();
    //获取TOP100
    List<FilmInfo> getTop100();

    /*
        获取影片条件接口
     */
    //获取分类条件
    List<CatVO> getCats();
    //获取片源条件
    List<SourceVO> getSources();
    //获取年份条件
    List<YearVO> getYears();
}
