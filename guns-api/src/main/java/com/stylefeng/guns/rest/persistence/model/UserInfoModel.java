package com.stylefeng.guns.rest.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Nemo
 * @version 1.0
 * @date 2019/6/6
 */
@Data
public class UserInfoModel implements Serializable {

    int uuid;
    String username;
    String nickname;
    String email;
    String phone;
    int sex;
    String birthday;
    int lifeState;
    String biography;
    String address;
    String headAddress;
    String updatetime;
    String createTime;

}
