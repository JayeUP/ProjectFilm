package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.rest.cinema.vo.CinemaInfoVO;
import com.stylefeng.guns.rest.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.rest.cinema.vo.HallInfoVO;
import lombok.Data;

/**
 * created by lziz
 * 2019/6/7 21:22
 */
@Data
public class CinemaFieldResponseVO {
    private CinemaInfoVO cinemaInfo;
    private FilmInfoVO filmInfo;
    private HallInfoVO hallInfo;
}
