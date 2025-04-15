package com.zja.artemis.config;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * @Author: zhengja
 * @Date: 2025-04-15 16:10
 */
@Configuration
public class JmsConfig {
    @Bean
    public Queue emailQueue() {
        return new ActiveMQQueue("email.queue");
    }
}