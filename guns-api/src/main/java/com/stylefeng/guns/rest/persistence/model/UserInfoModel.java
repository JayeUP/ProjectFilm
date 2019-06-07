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

    String uuid;
    String username;
    String nickname;
    String email;
    String phone;
    String sex;
    String birthday;
    String lifeState;
    String biography;
    String address;
    String headAddress;
    String updatetime;
    String createTime;

}
