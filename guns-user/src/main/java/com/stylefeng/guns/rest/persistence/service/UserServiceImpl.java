package com.stylefeng.guns.rest.persistence.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.rest.user.UserServiceAPI;
import com.stylefeng.guns.rest.user.model.UserInfoModel;
import com.stylefeng.guns.rest.user.model.UserModel;
import org.springframework.stereotype.Component;

/**
 * @author Nemo
 * @version 1.0
 * @date 2019/6/6
 */
@Component
@Service(interfaceClass = UserServiceAPI.class)
public class UserServiceImpl implements UserServiceAPI {
    @Override
    public boolean login(String username, String password) {
        System.out.println("this is user service implement! " + username + " " + password);
        return false;
    }

    @Override
    public boolean register(UserModel userModel) {
        return false;
    }

    @Override
    public boolean checkUsername(String username) {
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
