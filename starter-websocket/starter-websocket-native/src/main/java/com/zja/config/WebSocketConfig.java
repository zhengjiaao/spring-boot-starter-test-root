/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-01 10:59
 * @Since:
 */
package com.zja.config;

import com.zja.ws.HttpAuthHandler;
import com.zja.ws.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 注解 @EnableWebSocket 启用 websocket
 * 通过实现 WebSocketConfigurer 类并覆盖相应的方法进行 websocket 的配置，主要覆盖 registerWebSocketHandlers 这个方法
 * socket 示例：ws://localhost:8080/webroot/ws/auth/msg?token=1
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private HttpAuthHandler httpAuthHandler;
    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        /**
         * 添加 Handler 其中，paths 不可缺少
         * 添加 握手过滤器
         * 设置 关闭跨域校验  测试使用
         */
        registry.addHandler(httpAuthHandler, "/ws/auth/msg") // 同原生注解里的 @ServerEndpoint 功能
                .addInterceptors(authInterceptor)
                .setAllowedOriginPatterns("*");
//                .withSockJS(); // 若WebSocket不可用的话，使用 SockJS
    }
}
