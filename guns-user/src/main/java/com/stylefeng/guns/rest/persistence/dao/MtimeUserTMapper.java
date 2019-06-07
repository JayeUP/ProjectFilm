package com.stylefeng.guns.rest.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.persistence.model.MtimeUserT;
import com.stylefeng.guns.rest.persistence.model.UserInfoModel;
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

    MtimeUserT findUserByUsernameAndPassWord(@Param("username") String userName, @Param("password") String credenceCode);

    UserInfoModel findByUUID(@Param("uuid") Integer uuid);
}
