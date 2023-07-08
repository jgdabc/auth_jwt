package com.jgdabc.dao;


import com.jgdabc.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 兰舟千帆
 * @version 1.0
 * @date 2023/7/8 9:19
 * @Description 功能描述:
 */
@Mapper
public interface UserDao {
    User login(User user);
}
