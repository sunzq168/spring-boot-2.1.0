package com.sun.zq.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {
    Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("ServletContext 上下文初始化");
        System.out.println("ServletContext 上下文初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("ServletContext 上下文销毁");
        System.out.println("ServletContext 上下文销毁");
    }
}
