package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.rest.user.FilmUserServiceAPI;
import org.springframework.stereotype.Component;

/**
 * @author Nemo
 * @version 1.0
 * @date 2019/6/6
 */
@Component
@Service(interfaceClass = FilmUserServiceAPI.class)
public class FilmUserServiceImpl implements FilmUserServiceAPI {
    @Override
    public boolean login(String username, String password) {
        System.out.println("this is user service implement! " + username + " " + password);
        return false;
    }
}
