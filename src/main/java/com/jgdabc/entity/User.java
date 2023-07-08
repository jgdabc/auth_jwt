package com.jgdabc.entity;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author 兰舟千帆
 * @version 1.0
 * @date 2023/7/8 9:04
 * @Description 功能描述:实体类
 */
@Data

public class User {
    private String id;
    private String name;
    private String password;
}
