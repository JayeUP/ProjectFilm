package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.rest.cinema.vo.CinemaVO;
import lombok.Data;

import java.util.List;

@Data
public class CinemaListResponseVO {

    private List<CinemaVO> cinemas;

}
