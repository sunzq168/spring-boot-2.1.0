package com.sun.zq.service;

import com.sun.zq.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll() ;

    User save(User user) ;

    void deleteById(Integer id) ;

    Page<User> findAll(Pageable pageable);

    User findByName(String name);

    List<User> findByLikeName(String name);

    List<User> findByIn(List<Integer> idList);
}
