package com.stylefeng.guns.rest.cinema.vo;

import lombok.Data;

import java.util.List;

/**
 * created by lziz
 * 2019/6/7 20:20
 */
@Data
public class FilmInfoVO {
    private String filmId;
    private String filmName;
    private String cinemaId;
    private String filmLength;
    private String filmType;
    private String filmCats;
    private String actors;
    private String imgAddress;
    private List<FilmFieldVO> filmFields;
}
