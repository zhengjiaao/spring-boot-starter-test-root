package com.zja.amqp.rabbit.listener.config;

import com.zja.amqp.rabbit.listener.service.SyncConsumerService;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @Author: zhengja
 * @Date: 2025-01-10 13:46
 */
@Configurable
public class RabbitConfig {

/*
    public static final String QUEUE_NAME = "queue_name";
    public static final String EXCHANGE_NAME = "exchange_name";
    public static final String ROUTING_KEY = "routing_key";
    public static final String QUEUE_NAME_2 = "queue_name_2";
    public static final String EXCHANGE_NAME_2 = "exchange_name_2";
    public static final String ROUTING_KEY_2 = "routing_key_2";
*/

    // 创建消息队列
    // 创建一个配置类来声明消息队列
    @Bean
    public Queue syncQueue() {
        // durable: 是否持久化,默认是false,持久化队列
        return new Queue("syncQueue", true);
    }

/*    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("syncQueue");
        container.setMessageListener(listenerAdapter);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 手动确认
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(SyncConsumerService syncConsumerService) {
        return new MessageListenerAdapter(syncConsumerService, "receiveSyncMessage");
    }*/
}
