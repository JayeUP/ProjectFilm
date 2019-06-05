package com.stylefeng.guns.rest.film.vo;

import lombok.Data;

/**
 * Created by HorseXInsect
 * 2019/6/5 14:37
 */
@Data
public class FilmInfo {
    private String filmId;
    private int filmType;
    private String imgAddress;
    private String filmName;
    // 影片评分
    private String filmScore;
    // 想看影片的人数？？数据库里没有
    private int expectNum;
    private String showTime;
    private int boxNum;
    //
    private String score;

}
