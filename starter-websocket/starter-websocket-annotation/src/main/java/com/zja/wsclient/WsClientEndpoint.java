/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-01 10:37
 * @Since:
 */
package com.zja.wsclient;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import java.io.IOException;

/**
 * websocket 客户端
 */
@Slf4j
@ClientEndpoint
public class WsClientEndpoint {

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Client WebSocket is opening...");
        this.session = session;
        System.out.println("Client WebSocket onOpen sessionId:" + session.getId());
    }

    /**
     * 客户端接收服务端返回的消息
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("Websocket Client 接收到的消息: " + message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        session.close();
        System.out.println("Websocket Client onClose.");
    }

    @OnError
    public void onError(Session session, Throwable t) throws IOException {
        session.close();
        System.out.println("Websocket Client onError.");
        t.printStackTrace();
    }


    /**
     * 发送消息
     * @param message 消息
     */
    public void send(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 客户端 主动关闭连接
     */
    public void close() throws IOException {
        if (this.session.isOpen()) {
            this.session.close();
        }
    }

}
