package com.sun.zq.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Configurable
@Slf4j
public class SendMailQuartz {
    @Scheduled(cron = "*/5 * * * * *")
    public void reportCurrentByCron() {
        log.info("定时器运行了！");
    }
}
