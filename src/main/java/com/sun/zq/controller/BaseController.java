package com.sun.zq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        System.out.println("BaseController defaultErrorHandler....");
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.addObject("url", request.getRequestURL());
        mv.setViewName("errorResult");
        return mv;
    }
}
