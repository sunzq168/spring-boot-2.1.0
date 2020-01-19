package com.sun.zq.dao;

import com.sun.zq.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author sunzheng
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    /**
     * 根据用户ID查询用户的角色
     * @param userId
     * @return
     */
    List<UserRole> findByUserId(@Param("userId") Integer userId);
}
