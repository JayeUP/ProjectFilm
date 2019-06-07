package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.rest.cinema.vo.CinemaInfoVO;
import com.stylefeng.guns.rest.cinema.vo.FilmInfoVO;
import lombok.Data;

import java.util.List;

/**
 * created by lziz
 * 2019/6/5 16:26
 */
@Data
public class CinemaFieldsResponseVO {
    private CinemaInfoVO cinemaInfo;
    private List<FilmInfoVO> filmList;
}
