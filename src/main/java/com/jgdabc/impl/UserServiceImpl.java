package com.jgdabc.impl;

import com.jgdabc.Utils.JwtUtil;
import com.jgdabc.dao.UserDao;
import com.jgdabc.entity.User;
import com.jgdabc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 兰舟千帆
 * @version 1.0
 * @date 2023/7/8 10:06
 * @Description 功能描述:
 */
@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User login(User user) {
        User user_login = userDao.login(user);
        if (user_login!=null)
        {
            return user_login;
        }
        throw  new RuntimeException("登录失败");

    }

}
