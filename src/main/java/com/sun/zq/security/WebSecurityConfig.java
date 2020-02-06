package com.sun.zq.security;

import com.sun.zq.service.impl.CustomerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author sunzheng
 * @EnableWebSecurity 开启Security安全认证
 */
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;



    /**
     * 完成用户授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // authorizeRequests方法定义哪些URL需要被保护，哪些不需要被保护
        //http.authorizeRequests();
        // 路由策略和访问权限的简单控制
//        http.formLogin()    // 启用默认登录页面
//            .failureUrl("/login/fail")    //登录失败返回 URL : /login?error
//            .defaultSuccessUrl("/login/success") //登录成功跳转 URL，这里跳转到用户首页
//            .permitAll();  // 登录页面全部权限可访问

//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/","/user/save").hasRole("ADMIN")
//                .antMatchers("/user/list").hasAnyRole( "ADMIN", "USER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin() // 启用默认登录页面
//                //.loginPage("/login")//登录页面的访问地址
//                .usernameParameter("loginName").passwordParameter("password")
//                .failureUrl("/login?error")    //登录失败返回 URL : /login?error
//                .defaultSuccessUrl("/login/success") //登录成功跳转 URL，这里跳转到用户首页
//                .and()
//                .logout()//设置注销操作
//                .permitAll();  // 所有用户都可以访问


        http.authorizeRequests()
                .antMatchers("/login","/css/**","/js/**","/img/*").permitAll()
                .antMatchers("/", "/app/home").hasRole("USER")
                .antMatchers("/app/admin/**").hasAnyRole("ADMIN", "DBA")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginPage("/login")
                .successHandler(appAuthenticationSuccessHandler)
                .usernameParameter("loginName").passwordParameter("password")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/app/accessDenied");



        //super.configure(http);
    }

    /**
     * 完成用户认证
     * AuthenticationManagerBuilder 的inMemoryAuthentication方法可以添加用户，并给用户指定权限
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN", "DBA")
                .and()
                .withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
    }

    //@Bean
    public CustomerUserService customerUserService() {
        return new CustomerUserService();
    }

}
