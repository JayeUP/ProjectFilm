package com.stylefeng.guns.rest.cinema.vo;

import lombok.Data;

/**
 * created by lziz
 * 2019/6/7 21:03
 */
@Data
public class HallInfoVO {
    private String hallFieldId;
    private String hallName;
    private String price;
    private String seatFile;
    // 已售座位必须关联订单才能查询
    private String soldSeats;
}
