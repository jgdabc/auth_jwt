package com.jgdabc.auth_jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;


@SpringBootTest
class AuthJwtApplicationTests {

    //生成token
	@Test
	 String  buildToken() {
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.SECOND,90);
		String token = JWT.create()
				.withClaim("username","lll")
				.withClaim("id",1) //设置负载
				.withExpiresAt(instance.getTime()) //设置过期时间
				.sign(Algorithm.HMAC256("jgdabc"));//指定签名算法和签名所用秘钥串
		System.out.println(token);
		return  token;
	}
//	token验证
	@Test
	void VerifyToken()
	{
		JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("jgdabc")).build();
		DecodedJWT decodedJWT = jwtVerifier.verify(buildToken());
		String username = decodedJWT.getClaim("username").asString();//获取负载
		System.out.println(username);

	}



}
