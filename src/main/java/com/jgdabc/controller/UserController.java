package com.jgdabc.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.jgdabc.Utils.JwtUtil;
import com.jgdabc.entity.User;
import com.jgdabc.entity.User_N;
import com.jgdabc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 兰舟千帆
 * @version 1.0
 * @date 2023/7/8 10:20
 * @Description 功能描述:
 */
@RestController
@Slf4j
public class UserController {
    @Autowired

    private UserService userService;
    @PostMapping("/demo/test")
    public String testRequire(@RequestBody User_N user_n)
    {
        User_N sss = user_n.name("sss");
        System.out.println(sss);

        return "success";
    }


    @PostMapping ("/user/login/")
    public Map<String, Object> login( @RequestBody User user) {
        log.info("获取请求");

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();
        try {

            User userDB = userService.login(user);
            map.put("id", userDB.getId());
            map.put("username", userDB.getName());
            String token = JwtUtil.getToken(map);

            result.put("state",200);
            result.put("message","登录成功");
            result.put("token",token);
        } catch (Exception e) {
            result.put("state",false);
            result.put("msg",e.getMessage());
        }
        return  result;


    }
    @GetMapping("/users/test")
    public String test_demo(String token)
    {
        return "接口token校验成功";
    }




    }




