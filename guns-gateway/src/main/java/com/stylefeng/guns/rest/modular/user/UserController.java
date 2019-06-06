package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.user.UserService;
import com.stylefeng.guns.api.user.model.MtimeUserT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nemo
 * @version 1.0
 * @date 2019/6/6
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Reference(interfaceClass = UserService.class)
    private UserService userService;

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public Map checkUsername(String username) {
        HashMap map = new HashMap();

        boolean check = userService.checkUsername(username);

        if (!check) {
            map.put("status", 0);
            map.put("msg", "验证成功");
        } else {
            map.put("status", 1);
            map.put("msg", "用户已存在");
        }

        return map;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map register(MtimeUserT user) {
        HashMap map = new HashMap();

        String username = user.getUserName();
        boolean check = userService.checkUsername(username);

        String password = user.getUserPwd();

        if (!check && password!=null && !"".equals(password)) {
            boolean register = userService.register(user);

            if (register) {
                map.put("status", 0);
                map.put("msg", "注册成功");
            } else {
                map.put("status", 1);
                map.put("msg", "用户已存在");
            }
        } else {
            map.put("status", 1);
            map.put("msg", "用户已存在");
        }


        return map;
    }
}
