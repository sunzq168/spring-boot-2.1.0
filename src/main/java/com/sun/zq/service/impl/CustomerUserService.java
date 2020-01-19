package com.sun.zq.service.impl;

import com.google.common.collect.Lists;
import com.sun.zq.exception.AppBizException;
import com.sun.zq.model.Role;
import com.sun.zq.model.User;
import com.sun.zq.model.UserRole;
import com.sun.zq.service.RoleService;
import com.sun.zq.service.UserRoleService;
import com.sun.zq.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author sunzheng
 */
public class CustomerUserService implements UserDetailsService {
    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findByName(name);
        if (user == null) {
            log.info("user not exists, name= {]", name);
            throw new AppBizException("用户不存在");
        }
        List<GrantedAuthority> authorityList = Lists.newArrayList();
        List<UserRole> userRoleList = userRoleService.findByUserId(user.getId());
        userRoleList.forEach(userRole -> {
            Role role = roleService.findById(userRole.getId());
            if (role != null) {
                authorityList.add(new SimpleGrantedAuthority(role.getName()));
            }
        });

        //return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorityList);
        return new org.springframework.security.core.userdetails.User(user.getName(), "", authorityList);
    }
}
