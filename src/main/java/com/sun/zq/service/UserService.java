package com.sun.zq.service;

import com.sun.zq.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findById(Integer id);

    List<User> findAll() ;

    User save(User user) ;

    void deleteById(Integer id) ;

    Page<User> findAllByPage(Pageable pageable);

    Iterable<User> findAllBySort(Sort sort);

    User findByName(String name);

    List<User> findByLikeName(String name);

    List<User> findByIn(List<Integer> idList);

    List<User> findByIdIn(List<Integer> idList);

    List<User> findUserByName(String name);

    User findByLoginName(String loginName);


}
