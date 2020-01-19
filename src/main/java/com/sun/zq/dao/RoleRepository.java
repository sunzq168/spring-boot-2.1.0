package com.sun.zq.dao;

import com.sun.zq.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sunzheng
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
