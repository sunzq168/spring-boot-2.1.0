package com.sun.zq.service;


import com.sun.zq.dao.UserRepository;
import com.sun.zq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;
import java.util.List;
import java.util.Optional;

/**
 * @author sunzheng
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    //@Transactional(rollbackFor=RuntimeException.class, propagation = Propagation.REQUIRED)
    public User save(User user) {
        //this.deleteById(1);
        User saveUser = userRepository.save(user);
        int a = 2/0;

        return saveUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findByLikeName(String name) {
        return userRepository.findByNameLike(name);
    }

    @Override
    public List<User> findByIn(List<Integer> idList) {
        return userRepository.findByIdIn(idList);
    }
}
