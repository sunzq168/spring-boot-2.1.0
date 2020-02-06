package com.sun.zq.service.impl;


import com.google.common.collect.Lists;
import com.sun.zq.dao.RoleRepository;
import com.sun.zq.dao.UserRepository;
import com.sun.zq.dao.UserRoleRepository;
import com.sun.zq.exception.AppBizException;
import com.sun.zq.model.Role;
import com.sun.zq.model.User;
import com.sun.zq.model.UserRole;
import com.sun.zq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author sunzheng
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private UserRoleRepository userRoleRepository;

    @Resource
    private RoleRepository roleRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public List<User> findAll() {
        List<User> userList = (List<User>) redisTemplate.opsForList().range("userList", 0L, -1L);
        if (!CollectionUtils.isEmpty(userList)) {
            //return userList;
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
        //int a = 2/0;

        return saveUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findAllByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Iterable<User> findAllBySort(Sort sort) {
        return userRepository.findAll(sort);
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

    @Override
    public List<User> findUserByName(String name) {
        List<User> userList = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = criteriaBuilder.equal(root.get("name"), name);
                return p1;
            }
        });

        return userList;
    }

    @Override
    public User findByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User user = userRepository.findByLoginName(loginName);
        Assert.notNull(user, "用户名不存在");

        List<UserRole> userRoleList = userRoleRepository.findByUserId(user.getId());
        List<GrantedAuthority> authorityList = Lists.newArrayList();
        userRoleList.forEach(userRole -> {
            Optional<Role> role = roleRepository.findById(userRole.getRoleId());
            authorityList.add(new SimpleGrantedAuthority(role.get().getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorityList);
    }
}
