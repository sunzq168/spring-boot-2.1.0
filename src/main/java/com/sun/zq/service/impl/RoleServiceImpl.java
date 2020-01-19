package com.sun.zq.service.impl;

import com.sun.zq.dao.RoleRepository;
import com.sun.zq.model.Role;
import com.sun.zq.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author sunzheng
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findById(Integer id) {
        Optional<Role> role = roleRepository.findById(id);
        return  role.get();
    }
}
