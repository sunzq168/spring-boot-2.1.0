package com.sun.zq.security;

import com.google.common.collect.Lists;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
public class AppAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    /**
     * spring security 通过RedirectStrategy对象负责所有重定向事务
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 通过determineTargetUrl 方法返回需要跳转的URL
        String targetUrl = determineTargetUrl(authentication);
        // 重定向请求到指定的 URL
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * 从Authentication对象中提取当前登录用户的角色，并根据其角色返回适当的url
     * @return
     */
    protected String determineTargetUrl(Authentication authentication) {
        String url = "";
        // 获取当前登录用户的角色权限集合
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = Lists.newArrayList();

        // 将角色名称添加到list集合
        authorities.forEach(grantedAuthority -> {
            roles.add(grantedAuthority.getAuthority());
        });

        // 判断不同角色跳转到不同的URL
        if (isAdmin(roles)) {
            url = "/app/admin";
        } else if(isUser(roles)) {
            url = "/app/home";
        } else {
            url = "/app/accessDenied";
        }
        System.out.println("url = " + url);
        return url;
    }

    private boolean isAdmin(List<String> roles) {
        return roles.contains("ROLE_ADMIN");
    }

    private boolean isUser(List<String> roles) {
        return roles.contains("ROLE_USER");
    }

    @Override
    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}
