package com.sun.zq.controller;

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
}
