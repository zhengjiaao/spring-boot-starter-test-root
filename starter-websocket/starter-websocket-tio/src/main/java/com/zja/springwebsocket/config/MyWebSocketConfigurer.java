/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-29 16:38
 * @Since:
 */
package com.zja.springwebsocket.config;

import com.zja.springwebsocket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 *
 */
@Configuration
//@EnableWebSocket
public class MyWebSocketConfigurer implements WebSocketConfigurer {

    @Autowired
    private WebSocketHandler webSocketHandler;


    /**
     * 对WebSocket进行注册以及注入到Spring容器中
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // websocket连接地址 ws://localhost:8080/msg
        registry.addHandler(webSocketHandler, "/msg")
                // 允许跨域，方便本地调试，生产建议去掉
                .setAllowedOrigins("*");

                /**
                 * 老版本的浏览器不支持 WebSocket(url)连接，需要改用SockJS(url) http连接
                 * 客户端需要更改连接方式
                 * //var socket=new WebSocket(url);
                 * var socket=new SockJS(url);
                 */
//                .withSockJS();
    }
}
