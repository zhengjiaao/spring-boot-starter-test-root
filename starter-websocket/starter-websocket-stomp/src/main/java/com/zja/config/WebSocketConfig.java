package com.zja.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 配置 WebSocket STOMP 协议
 *
 * 注解 @EnableWebSocketMessageBroker 开启STOMP协议传输基于代理(message broker)的消息，使用@MessageMapping注解(类似@RequestMapping注解功能)
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册STOMP协议的节点(endpoint),并映射指定的url
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*");
//                .withSockJS();  //默认WebSocket协议，当WebSocket协议浏览器不支持时，使用SockJS协议
    }

    /**
     * 配置消息代理(Message Broker)
     *
     * 消息代理将会处理前缀为"/topic"和"/user"等的消息
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        /**
         * 设置客户端订阅消息的基础路径
         * 示例：ws://localhost:8080/webroot/ws/app/topic
         */
        registry.setApplicationDestinationPrefixes("/app");

        /**
         * 设置服务器广播消息的基础路径
         * 示例：queue* 和 user* 点对点，topic*广播，mass*群发，alone*单独聊天
         */
        registry.enableSimpleBroker("/queue", "/topic", "/user", "/mass", "/alone");

        /**
         * 设置服务器点对点消息的基础路径 订阅前缀: 点对点 (客户端订阅路径)
         * 默认：/user/
         */
        registry.setUserDestinationPrefix("/user/");
    }

}
