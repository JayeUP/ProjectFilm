package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeYearDictT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.film.vo.YearVO;

import java.util.List;

/**
 * <p>
 * 年代信息表 Mapper 接口
 * </p>
 *
 * @author jy
 * @since 2019-06-04
 */
public interface MtimeYearDictTMapper extends BaseMapper<MtimeYearDictT> {

    List<YearVO> getYears();
}
