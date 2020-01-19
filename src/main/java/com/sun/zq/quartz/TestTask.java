package com.sun.zq.quartz;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class TestTask {
   public void run() {
       log.info("定时器运行了！");
   }
}
