package com.sun.zq.service;

import com.sun.zq.model.Role;

/**
 * @author sunzheng
 */
public interface RoleService {

    /**
     * 根据角色id查询角色
     * @param id
     * @return
     */
    Role findById(Integer id);
}
