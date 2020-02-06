package com.sun.zq.controller;

import com.google.common.collect.Lists;
import com.sun.zq.exception.AppBizException;
import com.sun.zq.model.User;
import com.sun.zq.service.UserService;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String list(Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Iterable<User> userList = userService.findAllBySort(sort);
        model.addAttribute("users", userList);
        log.info("users = {}", JSONUtils.valueToString(userList));
        return "user";
    }

    @RequestMapping("/listPage")
    public String listPage(int pageIndex, Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageIndex -1, 2, sort);
        Page<User> userPage = userService.findAllByPage(pageable);
        System.out.println("查询总页数：" + userPage.getTotalPages());
        System.out.println("查询总记录数：" + userPage.getTotalElements());
        System.out.println("查询当前第几页：" + userPage.getNumber() + 1);
        System.out.println("查询当前页面的记录数：" + userPage.getNumberOfElements());
        model.addAttribute("users", userPage.getContent());
        log.info("users = {}", JSONUtils.valueToString(userPage.getContent()));
        return "user";
    }

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<User> userList =  userService.findAll();
        model.addAttribute("users", userList);
        throw  new AppBizException("业务异常");
    }

    @RequestMapping("/findById")
    public String findById(Model model) {
        List<Integer> ids = Lists.newArrayList();
        ids.add(1);
        ids.add(2);
        List<User> userList =  userService.findByIn(ids);

        model.addAttribute("users", userList);
        return "user";
    }

    @RequestMapping("/findByName")
    public String findById(String name, Model model) {
        List<User> userList =  userService.findUserByName(name);

        model.addAttribute("users", userList);
        return "user";
    }

    @RequestMapping("/save")
    public String save() {
        User user = new User();
        user.setName("sz");
        user.setAge(18);
        user.setEmail("sz@qq.com");

        userService.save(user);
        return "user_save_succ";
    }


    @RequestMapping("/add")
    public String add(String username) {
        System.out.println("UserController add()....");
        if (StringUtils.isBlank(username)) {
            throw  new NullPointerException("用户名称不能为空");
        }
        return "user";
    }



}
