package com.zja.amqp.rabbit.send.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @Author: zhengja
 * @Date: 2025-01-10 13:46
 */
@Configurable
public class RabbitConfig {

    public static final String QUEUE_NAME = "queue_name";
    public static final String EXCHANGE_NAME = "exchange_name";
    public static final String ROUTING_KEY = "routing_key";
    public static final String QUEUE_NAME_2 = "queue_name_2";
    public static final String EXCHANGE_NAME_2 = "exchange_name_2";
    public static final String ROUTING_KEY_2 = "routing_key_2";

    @Bean
    public Queue syncQueue() {
        // durable: 是否持久化,默认是false,持久化队列
        return new Queue("syncQueue", true);
    }
}
