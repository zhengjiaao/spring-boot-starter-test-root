package com.zja.stompwebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * springboot 配置 WebSocket STOMP
 *
 * 注解：@EnableWebSocketMessageBroker 开启STOMP协议传输基于代理(message broker)的消息，使用@MessageMapping注解(类似@RequestMapping注解功能)
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册STOMP协议的节点(endpoint),并映射指定的url
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")
                .setAllowedOriginPatterns("*")
//                .setAllowedOrigins("*")
                .withSockJS();  //SockJS协议
    }

    /**
     * 配置消息代理(Message Broker)
     *
     * 消息代理将会处理前缀为"/topic"和"/user"等的消息
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //应用程序以/app为前缀
        registry.setApplicationDestinationPrefixes("/app");

        //消息路径代理 以 "/topic", "/user", "/mass", "/alone" 为前缀
        // queue* 和 user* 点对点，topic*广播，mass*群发，alone*单独聊天
        registry.enableSimpleBroker("/queue", "/topic", "/user", "/mass", "/alone");

        //点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
//        registry.setUserDestinationPrefix("/user");
    }

}
