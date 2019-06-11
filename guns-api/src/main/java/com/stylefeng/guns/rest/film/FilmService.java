package com.stylefeng.guns.rest.film;


import com.stylefeng.guns.rest.film.vo.*;

import java.util.List;

public interface FilmService {

    /*获取首页接口*/
    //获取banners
    List<BannerVO> getBanners();
    //获取热映电影
    FilmVO getHotFilms(boolean isLimit,int nums);
    //获取即将上映影片（受欢迎程度做排序）
    FilmVO getSoonFilms(boolean isLimit,int nums);
    //获取票房排行榜
    List<FilmInfo> getBoxRanking();
    //获取人气排行榜
    List<FilmInfo> getExpectRanking();
    //获取Top100
    List<FilmInfo> getTop();

    /*
        获取影片条件接口
     */
    //获取分类条件
    List<CatVO> getCats();
    //获取片源条件
    List<SourceVO> getSources();
    //获取年份条件
    List<YearVO> getYears();

    /*影片查询接口*/
    //获取热映电影
    FilmVO getHotFilms(boolean isLimit,int nums,int nowPage,int sortId,int sourceId,int yearId,int catId);
    //获取即将上映影片（受欢迎程度做排序）
    FilmVO getSoonFilms(boolean isLimit,int nums,int nowPage,int sortId,int sourceId,int yearId,int catId);
    //获取经典影片
    FilmVO getClassicFilms(int nums,int nowPage,int sortId,int sourceId,int yearId,int catId);

    /*影片详情查询接口*/
    //获取影片ID或者名称获取影片信息
    FilmDetailVO getFilmDetail(int searchType,String searchParam);

    //获取影片相关的其他信息【演员表、图片地址..】

    //获取影片描述信息
    FilmDescVO getFilmDesc(String filmId);
    //获取图片信息
    ImgVO getImgs(String filmId);
    //获取导演信息
    ActorVO getDectInfo(String filmId);
    //获取演员信息
    List<ActorVO> getActors(String filmId);
}
