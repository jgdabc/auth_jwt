package com.jgdabc.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 兰舟千帆
 * @version 1.0
 * @date 2023/7/8 12:12
 * @Description 功能描述:
 */
@Data
@Accessors(fluent = true)
public class User_N {
    private String id;
    private String name;
    private String password;
}
