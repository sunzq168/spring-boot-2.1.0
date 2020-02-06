package com.sun.zq.controller;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandlerController {
    @ExceptionHandler(value = Exception.class)
    //@ResponseBody
    public Object globalErrorHandler(HttpServletRequest request, Exception e) {
        System.out.println("GlobalExceptionHandlerController globalErrorHandler....");
        Map<String, Object> mv = Maps.newHashMap();
        mv.put("code", 999);
        mv.put("message", e.getMessage());
        mv.put("url", request.getRequestURL().toString());
        mv.put("data", "请求失败");

        return mv;
    }
}
