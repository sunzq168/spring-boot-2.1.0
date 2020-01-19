package com.sun.zq.security;

import com.sun.zq.service.impl.CustomerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @EnableWebSecurity 开启Security安全认证
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 路由策略和访问权限的简单控制
        http.formLogin()    //启用默认登录页面
            .failureUrl("/login?error")    //登录失败返回 URL : /login?error
            .defaultSuccessUrl("/user/list") //登录成功跳转 URL，这里跳转到用户首页
            .permitAll();  // 登录页面全部权限可访问
        super.configure(http);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN")
                .and()
                .withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
    }

    //@Bean
    public CustomerUserService customerUserService() {
        return new CustomerUserService();
    }

}
