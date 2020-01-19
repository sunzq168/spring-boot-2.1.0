package com.sun.zq.service;

import com.sun.zq.model.UserRole;

import java.util.List;

/**
 * @author sunzheng
 */
public interface UserRoleService {
    /**
     * 根据用户ID，查询用户的角色
     * @param userId
     * @return
     */
    List<UserRole> findByUserId(Integer userId) ;
}
