package com.stylefeng.guns.rest.persistence;

import com.stylefeng.guns.rest.persistence.model.MtimeUserT;
import com.stylefeng.guns.rest.persistence.model.UserInfoModel;
import com.stylefeng.guns.rest.persistence.model.UserModel;

/**
 * @author Nemo
 * @version 1.0
 * @date 2019/6/6
 */

public interface UserService {

    public boolean login(String username, String password);

    public boolean register(UserModel user);

    public boolean checkUsername(String username);

    UserInfoModel getUserInfo(int uuid);

    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);
}
