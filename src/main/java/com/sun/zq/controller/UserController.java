package com.sun.zq.controller;

import com.google.common.collect.Lists;
import com.sun.zq.exception.AppBizException;
import com.sun.zq.model.User;
import com.sun.zq.service.UserService;
import net.sf.json.util.JSONUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<User> userList =  userService.findAll();
        model.addAttribute("users", userList);
        log.info("users = {}", JSONUtils.valueToString(userList));
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
        List<User> userList =  userService.findByIdIn(ids);

        model.addAttribute("users", userList);
        return "user";
    }

}
