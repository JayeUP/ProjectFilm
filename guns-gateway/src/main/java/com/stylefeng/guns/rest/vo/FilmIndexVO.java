package com.stylefeng.guns.rest.vo;

import com.stylefeng.guns.rest.film.vo.BannerVO;
import com.stylefeng.guns.rest.film.vo.FilmInfo;
import com.stylefeng.guns.rest.film.vo.FilmVO;
import lombok.Data;

import java.util.List;

@Data
public class FilmIndexVO {
    List<BannerVO> banners;
    FilmVO hotFilms;
    FilmVO soonFilms;
    List<FilmInfo> boxRanking;
    List<FilmInfo> expectRanking;
    List<FilmInfo> top100;
}
