package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeCatDictT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.film.vo.CatVO;

import java.util.List;

/**
 * <p>
 * 类型信息表 Mapper 接口
 * </p>
 *
 * @author jy
 * @since 2019-06-04
 */
public interface MtimeCatDictTMapper extends BaseMapper<MtimeCatDictT> {

    List<CatVO> getCats();

}
