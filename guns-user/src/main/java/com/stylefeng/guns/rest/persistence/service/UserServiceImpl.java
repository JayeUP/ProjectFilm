package com.stylefeng.guns.rest.persistence.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.rest.config.properties.JwtProperties;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.persistence.UserService;
import com.stylefeng.guns.rest.persistence.model.MtimeUserT;
import com.stylefeng.guns.rest.persistence.model.UserInfoModel;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.persistence.dao.MtimeUserTMapper;
import com.stylefeng.guns.rest.persistence.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public int login(String username, String password) {
        return 3;
    }

    @Override
    public boolean register(UserModel user) {
        // 注册成功返回true, 否则返回false

        // MD5加密密码
        String password = user.getPassword();
        String encrypt = MD5Util.encrypt(password);
        user.setPassword(encrypt);

        MtimeUserT mtimeUserT = new MtimeUserT();
        mtimeUserT.setUserName(user.getUsername());
        mtimeUserT.setUserPwd(user.getPassword());
        mtimeUserT.setEmail(user.getEmail());
        mtimeUserT.setUserPhone(user.getPhone());
        mtimeUserT.setAddress(user.getAddress());

        Integer insert = userMapper.insert(mtimeUserT);

        return insert != 0;
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


        UserInfoModel userInfo = userMapper.findByUUID(uuid);

        if (userInfo != null) {
            return userInfo;
        } else {
            return null;
        }
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        MtimeUserT mtimeUserT = new MtimeUserT();
        mtimeUserT.setUuid(userInfoModel.getUuid());
        mtimeUserT.setNickName(userInfoModel.getNickname());
        mtimeUserT.setEmail(userInfoModel.getEmail());
        mtimeUserT.setUserPhone(userInfoModel.getPhone());
        mtimeUserT.setUserSex(userInfoModel.getSex());
        mtimeUserT.setBirthday(userInfoModel.getBirthday());
        mtimeUserT.setLifeState(userInfoModel.getLifeState());
        mtimeUserT.setBiography(userInfoModel.getBiography());
        mtimeUserT.setAddress(userInfoModel.getAddress());

        Integer integer = userMapper.updateById(mtimeUserT);

        if (integer == 0) {
            return null;
        } else {
            return getUserInfo(mtimeUserT.getUuid());
        }


    }

    @Override
    public MtimeUserT findUserByUsernameAndPassWord(String userName, String credenceCode) {
        credenceCode = MD5Util.encrypt(credenceCode);

        return userMapper.findUserByUsernameAndPassWord(userName, credenceCode);
    }
}
