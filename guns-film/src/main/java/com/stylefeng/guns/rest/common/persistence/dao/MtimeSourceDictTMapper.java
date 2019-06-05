package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeSourceDictT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.film.vo.SourceVO;

import java.util.List;

/**
 * <p>
 * 区域信息表 Mapper 接口
 * </p>
 *
 * @author jy
 * @since 2019-06-04
 */
public interface MtimeSourceDictTMapper extends BaseMapper<MtimeSourceDictT> {

    List<SourceVO> getSources();

}
