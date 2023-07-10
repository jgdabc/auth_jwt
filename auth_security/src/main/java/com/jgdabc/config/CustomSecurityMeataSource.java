package com.jgdabc.config;

import com.jgdabc.entity.Menu;
import com.jgdabc.service.MenuService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author 兰舟千帆
 * @version 1.0
 * @date 2023/7/10 16:26
 * @Description 功能描述:
 */
@Component
public class CustomSecurityMeataSource implements FilterInvocationSecurityMetadataSource {
    private final MenuService menuService;

    public CustomSecurityMeataSource(MenuService menuService) {
        this.menuService = menuService;
    }

    AntPathMatcher antPathMatcher = new AntPathMatcher();
    //   自定义动态资源权限元数据信息
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        获取当前的请求对象
        String requestURI = ((FilterInvocation) object).getRequest().getRequestURI();
        List<Menu> allMenu = menuService.getAllMenu();
//        /遍历资源，查找用户访问的资源
        for (Menu menu : allMenu) {
            //判断请求url与菜单角色是否匹配
            if (antPathMatcher.match(menu.getPattern(), requestURI)) {
                //将url匹配到的菜单所需要的所有角色加入到Security中
                String[] roles = menu.getRoles().stream().map(r -> r.getName()).toArray(String[]::new);
                return SecurityConfig.createList(roles);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
