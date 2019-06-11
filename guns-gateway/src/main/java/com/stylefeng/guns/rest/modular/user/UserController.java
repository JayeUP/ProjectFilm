package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.config.properties.JwtProperties;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.persistence.UserService;
import com.stylefeng.guns.rest.persistence.model.MtimeUserT;
import com.stylefeng.guns.rest.persistence.model.UserInfoModel;
import com.stylefeng.guns.rest.persistence.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;


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
    public Map register(UserModel user) {
        HashMap map = new HashMap();

        String username = user.getUsername();
        boolean check = userService.checkUsername(username);

        String password = user.getPassword();

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

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public Map getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        HashMap map = new HashMap();

        String header = request.getHeader(jwtProperties.getHeader());
        String authToken = null;

        // System.out.println(header);

        if (header != null && header.startsWith("Bearer ")) {
            authToken = header.substring(7);
        }

        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(authToken);

        // System.out.println("usernameFromToken = " + usernameFromToken);

        // MtimeUserT mtimeUserT = JSONObject.parseObject(usernameFromToken, MtimeUserT.class);

        UserInfoModel userInfo = userService.getUserInfo(Integer.parseInt(usernameFromToken));

        if (userInfo != null) {
            map.put("status", 0);
            map.put("data", userInfo);
        } else {
            map.put("status", 1);
            map.put("msg", "查询失败，用户尚未登录");
        }

        return map;
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map updateUserInfo(UserInfoModel userInfo) {
        HashMap map = new HashMap();

        String userId = CurrentUser.getCurrentUser();
        if (userId != null && !"".equals(userId)) {
            int uuid = Integer.parseInt(userId);
            userInfo.setUuid(uuid);
        }

        UserInfoModel update = userService.updateUserInfo(userInfo);

        if (update != null) {
            map.put("status", 0);
            map.put("data", update);
        } else {
            map.put("status", 1);
            map.put("msg", "用户信息修改失败");
        }

        return map;
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Map logout(HttpServletRequest request, HttpServletResponse response) {
        HashMap map = new HashMap();

        String header = request.getHeader(jwtProperties.getHeader());
        String authToken = null;

        // System.out.println(header);

        if (header != null && header.startsWith("Bearer ")) {
            authToken = header.substring(7);
        }

        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(authToken);


        map.put("status", 0);
        map.put("msg", "成功退出");


        return map;
    }

}
