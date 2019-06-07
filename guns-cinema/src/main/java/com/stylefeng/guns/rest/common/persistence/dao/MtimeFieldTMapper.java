package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.rest.cinema.vo.HallInfoVO;
import com.stylefeng.guns.rest.common.persistence.model.MtimeFieldT;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author lziz
 * @since 2019-06-04
 */
public interface MtimeFieldTMapper extends BaseMapper<MtimeFieldT> {

    List<FilmInfoVO> getFilmInfos(int cinemaId);

    FilmInfoVO getFilmInfoById(Integer fieldId);

    HallInfoVO getHallInfo(Integer fieldId);
}
