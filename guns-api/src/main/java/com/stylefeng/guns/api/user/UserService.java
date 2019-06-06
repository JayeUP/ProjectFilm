package com.stylefeng.guns.api.user;

import com.stylefeng.guns.api.user.model.MtimeUserT;
import com.stylefeng.guns.api.user.model.UserInfoModel;

/**
 * @author Nemo
 * @version 1.0
 * @date 2019/6/6
 */

public interface UserService {

    public boolean login(String username, String password);

    public boolean register(MtimeUserT user);

    public boolean checkUsername(String username);

    UserInfoModel getUserInfo(int uuid);

    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);
}
