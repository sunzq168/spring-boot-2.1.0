package com.sun.zq.dao;

import com.sun.zq.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author sunzheng
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    List<User> findByNameLike(String name);

    List<User> findByIdIn(Collection<Integer> ids);
}
