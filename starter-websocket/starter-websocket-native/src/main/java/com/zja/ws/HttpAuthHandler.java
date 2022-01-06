/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-12-13 16:26
 * @Since:
 */
package com.zja.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;

/**
 * websocket消息处理器：相当于controller 的 Handler处理器
 * Handler 同原生注解里的 @ServerEndpoint 功能
 */
@Slf4j
@Component
public class HttpAuthHandler extends TextWebSocketHandler {

    /**
     * socket连接成功后触发
     * 在 socket 连接成功后被触发，同原生注解里的 @OnOpen 功能
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Object token = session.getAttributes().get("token");
        if (token != null) {
            // 用户连接成功，放入在线用户缓存
            WsSessionManager.add(token.toString(), session);
        } else {
            throw new RuntimeException("用户登录已经失效!");
        }
    }

    /**
     * 客户端发送普通文件信息时触发
     * 在客户端发送信息时触发，同原生注解里的 @OnMessage 功能
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获得客户端传来的消息
        String payload = message.getPayload();
        Object token = session.getAttributes().get("token");
        System.out.println("server 接收到 " + token + " 发送的 " + payload);
        session.sendMessage(new TextMessage("server 发送给 " + token + " 消息 " + payload + " " + LocalDateTime.now().toString()));
    }

    /**
     * socket连接关闭后触发
     * 在 socket 连接关闭后被触发，同原生注解里的 @OnClose 功能
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Object token = session.getAttributes().get("token");
        if (token != null) {
            // 用户退出，移除缓存
            WsSessionManager.remove(token.toString());
        }
    }

    /**
     * 处理传输错误时触发
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("异常处理" );
        exception.printStackTrace();
    }
}
