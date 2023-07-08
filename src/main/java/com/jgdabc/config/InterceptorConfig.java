package com.jgdabc.config;

import com.jgdabc.Hander.JwtHander;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 兰舟千帆
 * @version 1.0
 * @date 2023/7/8 12:58
 * @Description 功能描述:
 */
@Component
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new JwtHander())
               .excludePathPatterns("/user/**").addPathPatterns("/**");


    }
}
