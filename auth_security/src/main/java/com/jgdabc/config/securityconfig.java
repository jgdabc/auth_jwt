package com.jgdabc.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author 兰舟千帆
 * @version 1.0
 * @date 2023/7/10 15:53
 * @Description 功能描述:
 */
@Configuration
public class securityconfig extends WebSecurityConfigurerAdapter {


    public securityconfig(FilterInvocationSecurityMetadataSource securityMetadataSource, UserDetailsService userDetailsService) {
        this.securityMetadataSource = securityMetadataSource;
        this.userDetailsService = userDetailsService;
    }


    private final FilterInvocationSecurityMetadataSource securityMetadataSource;

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
        http.apply(new UrlAuthorizationConfigurer<>(applicationContext))
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(securityMetadataSource);
//                        是否拒绝公共资源的访问
                        object.setRejectPublicInvocations(false);
                        return object;
                    }
                });
        http.formLogin()
                .and()
                .csrf().disable();
    }

    }

