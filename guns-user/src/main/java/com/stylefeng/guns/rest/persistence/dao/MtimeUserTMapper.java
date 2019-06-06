package com.stylefeng.guns.rest.persistence.dao;

import com.stylefeng.guns.api.persistence.model.MtimeUserT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Nemo Wang
 * @since 2019-06-06
 */
public interface MtimeUserTMapper extends BaseMapper<MtimeUserT> {

    int findByUsername(@Param("username") String username);

}
