package com.sun.zq.quartz;

import com.sun.zq.mail.SendMailService;
import com.sun.zq.model.User;
import com.sun.zq.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
@Configurable
@Slf4j
public class SendMailQuartz {
    @Autowired
    private UserService userService;

    //@Autowired
    //private SendMailService sendMailService;

    @Scheduled(cron = "*/5 * * * * *")
    public void reportCurrentByCron() {
        log.info("定时器运行了！");
        List<User> userList = userService.findAll();

        //sendMailService.sendMail(userList);
    }
}
