package com.sun.zq.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MqConsumer {
    @JmsListener(destination = "testMq.queue")
    public void receive(String text) {
        System.out.println("消费消息：" + text);
    }
}
