package com.stylefeng.guns.rest.persistence.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.user.UserService;
import com.stylefeng.guns.api.user.model.MtimeUserT;
import com.stylefeng.guns.api.user.model.UserInfoModel;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.api.persistence.dao.MtimeUserTMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Nemo
 * @version 1.0
 * @date 2019/6/6
 */
@Component
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private MtimeUserTMapper userMapper;

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean register(MtimeUserT user) {
        // 注册成功返回true, 否则返回false

        // MD5加密密码
        String password = user.getUserPwd();
        String encrypt = MD5Util.encrypt(password);
        user.setUserPwd(encrypt);



        Integer insert = userMapper.insert(user);

        return false;
    }

    @Override
    public boolean checkUsername(String username) {
        // 存在返回true, 可用返回false

        if (username != null && !"".equals(username)) {
            int i = userMapper.findByUsername(username);
            return i != 0;
        }
        return false;
    }

    @Override
    public UserInfoModel getUserInfo(int uuid) {
        return null;
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        return null;
    }
}
