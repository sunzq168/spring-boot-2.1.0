package com.sun.zq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sunzheng
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/fail")
    public String loginFail() {
        System.out.println("login fail...");

        return "loginFail";
    }

    @RequestMapping("/success")
    public String loginSucc() {
        System.out.println("login succ...");

        return "loginSucc";
    }
}
