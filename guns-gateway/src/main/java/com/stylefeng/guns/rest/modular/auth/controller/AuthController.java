package com.stylefeng.guns.rest.modular.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.rest.common.exception.BizExceptionEnum;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.persistence.UserService;
import com.stylefeng.guns.rest.persistence.model.MtimeUserT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Reference(interfaceClass = UserService.class,check = false)
    private UserService userService;

    @RequestMapping(value = "${jwt.auth-path}")
    public /*ResponseEntity<?>*/Map createAuthenticationToken(AuthRequest authRequest) {

        /*boolean validate = true;

        int uuid = userService.login(authRequest.getUserName(), authRequest.getPassword());

        if (uuid == 0) {
            validate = false;
        }

        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(""+uuid, randomKey);
            return ResponseEntity.ok(new AuthResponse(token, randomKey));
        } else {
            throw new GunsException(BizExceptionEnum.AUTH_REQUEST_ERROR);
        }*/

        HashMap map = new HashMap();
        MtimeUserT user = userService.findUserByUsernameAndPassWord(authRequest.getUserName(), authRequest.getCredenceCode());

        if (user!=null) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken("" + user.getUuid(), randomKey);
            ResponseEntity<AuthResponse> ok = ResponseEntity.ok(new AuthResponse(token, randomKey));
            AuthResponse body = ok.getBody();
            map.put("status",0);
            map.put("data",body);

            // System.out.println("AuthController");

        } else {
            map.put("status",1);
            map.put("msg",new GunsException(BizExceptionEnum.AUTH_REQUEST_ERROR));
        }
        return map;
    }
}
