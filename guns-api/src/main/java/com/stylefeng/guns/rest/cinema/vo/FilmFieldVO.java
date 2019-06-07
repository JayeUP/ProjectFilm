package com.stylefeng.guns.rest.cinema.vo;

import lombok.Data;

/**
 * created by lziz
 * 2019/6/7 20:21
 */
@Data
public class FilmFieldVO {
    private String cinemaId;
    private String fieldId;
    private String beginTime;
    private String endTime;
    private String language;
    private String hallName;
    private String price;
}
