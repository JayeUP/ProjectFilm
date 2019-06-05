package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeBannerT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.film.vo.BannerVO;

import java.util.List;

/**
 * <p>
 * banner信息表 Mapper 接口
 * </p>
 *
 * @author jy
 * @since 2019-06-04
 */
public interface MtimeBannerTMapper extends BaseMapper<MtimeBannerT> {

    List<BannerVO> getBanners();
}
