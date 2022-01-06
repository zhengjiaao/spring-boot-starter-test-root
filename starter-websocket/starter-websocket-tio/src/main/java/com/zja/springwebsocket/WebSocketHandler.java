/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-29 16:37
 * @Since:
 */
package com.zja.springwebsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * websocket消息处理器：相当于controller的处理器
 */
@Slf4j
@Component
public class WebSocketHandler extends AbstractWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //socket连接成功后触发
        log.info("建立websocket连接");
        WebSocketSessionManager.add(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 客户端发送普通文件信息时触发
        log.info("发送文本消息");
        // 获得客户端传来的消息
        String payload = message.getPayload();
        log.info("服务端接收到消息 " + payload);
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        //客户端发送二进信息是触发
        log.info("发送二进制消息");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        //异常时触发
        log.error("异常处理");
        WebSocketSessionManager.removeAndClose(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // socket连接关闭后触发
        log.info("关闭websocket连接");
        WebSocketSessionManager.removeAndClose(session.getId());
    }
}
