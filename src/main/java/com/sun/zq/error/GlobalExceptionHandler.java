package com.sun.zq.error;

import com.sun.zq.exception.AppBizException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = {"com.sun.zq",} )
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({AppBizException.class})
    public ErrorInfo errorHandler(HttpServletRequest request, Exception e) throws Exception {
        //ErrorInfo errorInfo = new ErrorInfo();
        //errorInfo.setCode(ErrorInfo.SUCCESS);
        //errorInfo.setMessage(e.getMessage());
       // errorInfo.setUrl(request.getRequestURI());

        return new ErrorInfo(ErrorInfo.SUCCESS, e.getMessage(), request.getRequestURI());
    }

}
