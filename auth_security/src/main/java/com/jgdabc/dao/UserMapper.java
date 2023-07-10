package com.jgdabc.dao;

import com.jgdabc.entity.Role;
import com.jgdabc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
//    根据用户id获取角色信息
    List<Role> getUserRoleByUid(Integer uid);
//    根据用户名获取用户
    User loadUserByUsername(String username);
}
