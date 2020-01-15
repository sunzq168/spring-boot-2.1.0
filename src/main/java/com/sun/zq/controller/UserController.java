package com.sun.zq.controller;

import com.sun.zq.model.User;
import com.sun.zq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<User> userList =  userService.findAll();
        model.addAttribute("users", userList);
        return "user";
    }
}
