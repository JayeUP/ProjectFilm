package com.stylefeng.guns.rest.film;

import com.stylefeng.guns.rest.film.vo.FilmVO;

public interface FilmService {

    String test();

    // 获取正在热映电影
    FilmVO getHotFilms(int nowPage, int pageSize, int sortId, int catId, int sourceId, int yearId);

    // 获取即将上映电影
    FilmVO getSoonFilms(int nowPage, int pageSize, int sortId, int catId, int sourceId, int yearId);

    // 获取经典影片
    FilmVO getClassicFilms(int nowPage, int pageSize, int sortId, int catId, int sourceId, int yearId);

}
