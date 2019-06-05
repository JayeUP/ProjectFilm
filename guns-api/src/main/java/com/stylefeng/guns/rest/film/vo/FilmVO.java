package com.stylefeng.guns.rest.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HorseXInsect
 * 2019/6/4 23:42
 */
@Data
public class FilmVO implements Serializable {

    private int filmNum;
    // 影片列表当前页
    private int nowPage;
    // 总页数
    private int totalPage;
    // 电影信息
    private List<FilmInfo> filmInfo;

}
