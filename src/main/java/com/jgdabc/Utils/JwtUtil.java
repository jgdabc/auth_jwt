package com.jgdabc.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Map;

/**
 * @author 兰舟千帆
 * @version 1.0
 * @date 2023/7/8 10:32
 * @Description 功能描述:
 */
public class JwtUtil {
    private static String masterKey = "jgdabc";//指定秘钥
    /**
     * 生成token
     * @param map  //传入payload
     * @return 返回token
     */
    public static String getToken(Map<String,String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,7);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(masterKey)).toString();
    }
    /**
     * 验证token
     * @param token
     * @return
     */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(masterKey)).build().verify(token);
    }

}
