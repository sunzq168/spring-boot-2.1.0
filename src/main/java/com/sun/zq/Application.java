package com.sun.zq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ServletComponentScan:使用该注解后， Serviet、 Filter、 Listener可以直接通 过@WebServlet、 @WebFilter、@WebListener注解 自动注册，无须其他代码
 *
 * @EnableAsync 开启异步调用
 * @EnableRetry 开启重试机制
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@ImportResource(locations = "classpath:spring-mvc.xml")
@EnableAsync
@EnableRetry
public class Application {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(Application.class, args);
		System.out.println("Application started!");
	}

}
