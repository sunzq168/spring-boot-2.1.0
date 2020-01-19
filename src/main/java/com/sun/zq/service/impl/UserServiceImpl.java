package com.sun.zq.service.impl;


import com.sun.zq.dao.UserRepository;
import com.sun.zq.exception.AppBizException;
import com.sun.zq.model.User;
import com.sun.zq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author sunzheng
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public List<User> findAll() {
        List<User> userList = (List<User>) redisTemplate.opsForList().range("userList", 0L, -1L);
        if (!CollectionUtils.isEmpty(userList)) {
            return userList;
        }
        userList = userRepository.findAll();
        redisTemplate.opsForList().leftPushAll("userList", userList);
        return userList;
    }

    @Override
    @Transactional(rollbackFor=RuntimeException.class, propagation = Propagation.REQUIRED)
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

    @Retryable(value = {AppBizException.class}, maxAttempts = 8,backoff = @Backoff(delay = 2000, multiplier = 2))
    @Override
    public List<User> findByIdIn(List<Integer> idList) {
        System.out.println("findByIdIn 重试失败了！" + new Date());
        throw new AppBizException();
    }
}
