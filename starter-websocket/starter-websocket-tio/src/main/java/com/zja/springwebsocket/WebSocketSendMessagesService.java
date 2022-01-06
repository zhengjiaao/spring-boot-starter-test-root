/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-29 16:40
 * @Since:
 */
package com.zja.springwebsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * WebSocket 发送消息
 */
@Service
@Slf4j
public class WebSocketSendMessagesService {


    /**
     * 发送消息
     *
     * @param session
     * @param text
     * @return
     * @throws IOException
     */
    public void sendMsg(WebSocketSession session, String text) throws IOException {
        session.sendMessage(new TextMessage(text));
    }

    /**
     * 广播消息
     *
     * @param text
     * @return
     * @throws IOException
     */
    public void broadcastMsg(String text) throws IOException {
        for (WebSocketSession session : WebSocketSessionManager.SESSION_POOL.values()) {
            session.sendMessage(new TextMessage(text));
        }
    }

}
