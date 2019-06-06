package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.user.UserServiceAPI;
import org.springframework.stereotype.Component;

/**
 * @author Nemo
 * @version 1.0
 * @date 2019/6/6
 */
@Component
public class Client {

    @Reference(interfaceClass = UserServiceAPI.class)
    private UserServiceAPI userServiceAPI;

    public void run() {
        userServiceAPI.login("admin", "password");
    }
}
