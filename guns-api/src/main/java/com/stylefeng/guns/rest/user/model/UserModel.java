package com.stylefeng.guns.rest.user.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Nemo
 * @version 1.0
 * @date 2019/6/6
 */
@Data
public class UserModel implements Serializable {

    private String username;

    private String password;

    private String email;

    private String phone;

    private String address;
}
