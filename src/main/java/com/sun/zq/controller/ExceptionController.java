package com.sun.zq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController extends BaseController{
    @RequestMapping("/exception")
    public String index() {
        return "exception";
    }

    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception();
    }

    @RequestMapping("/test")
    public String test() throws Exception {
        int i = 5/0;
        return "success";
    }

//    @ExceptionHandler(value = Exception.class)
//    public String testErrorHandler() throws Exception {
//        System.out.println("testErrorHandler...");
//        return "服务器故障，请联系管理员。";
//    }

}
