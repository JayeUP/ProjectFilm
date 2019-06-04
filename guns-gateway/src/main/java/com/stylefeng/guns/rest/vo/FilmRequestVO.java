package com.stylefeng.guns.rest.vo;

import lombok.Data;

/**
 * Created by HorseXInsect
 * 2019/6/4 23:25
 */
@Data
public class FilmRequestVO {

    // 查询类型
    private int showType;
    // 排序方式
    private int sortId;
    // 类型编号
    private int catId;
    // 区域编号
    private int sourceId;
    // 年代编号
    private int yearId;
    // 影片列表当前页
    private int nowPage;
    // 每页显示条数
    private int pageSize;
}
