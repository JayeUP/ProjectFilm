package com.stylefeng.guns.rest.user;

import com.stylefeng.guns.rest.user.model.UserInfoModel;
import com.stylefeng.guns.rest.user.model.UserModel;

/**
 * @author Nemo
 * @version 1.0
 * @date 2019/6/6
 */

public interface UserServiceAPI {

    public boolean login(String username, String password);

    public boolean register(UserModel userModel);

    public boolean checkUsername(String username);

    UserInfoModel getUserInfo(int uuid);

    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);
}
