/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-01 10:24
 * @Since:
 */
package com.zja.wsservice;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket 服务端
 * 在线测试：http://coolaf.com/tool/chattest
 * ws://127.0.0.1:8080/webroot/ws/server/v1
 */
@ServerEndpoint("/ws/server/v1")  // 类似 @RequestMapping
@Component
public class WsServerEndpoint {

    //与某个客户端的连接对话，需要通过它来给客户端发送消息
    private Session session;

    /**
     *  用于存所有的连接服务的客户端，这个对象存储是安全的
     */
    private static ConcurrentHashMap<String, WsServerEndpoint> webSocketPut = new ConcurrentHashMap<>();


    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketPut.put(session.getId(), this);
        System.out.println("websocket server Session 连接成功：" + session.getId());
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        webSocketPut.remove(this.session.getId());
        session.close();

        System.out.println("websocket server 连接关闭：" + session.getId());
    }

    /**
     * 异常错误-连接关闭
     *
     * @param session
     */
    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        webSocketPut.remove(this.session.getId());
        session.close();

        System.out.println("websocket server 异常连接关闭：" + session.getId());
        throwable.printStackTrace();
    }

    /**
     * 接收到消息
     *
     * @param message
     */
    @OnMessage
    public String onMessage(String message) throws IOException {
        System.out.println("websocket server 来自客户端消息：" + message);

        //发送消息给指定的人
//        this.sendMessageByUserId(message, meetingId, userId);

        /**
         * 回复给客户端消息
         */
        return "websocket server 服务端返回的消息：" + message;
    }

    /**
     * 获取所有的websocket连接
     */
    public ConcurrentHashMap<String, WsServerEndpoint> getAllWebSocketPut() {
        return webSocketPut;
    }

    /**
     * 获取session
     * @param key
     * @return Session
     */
    public Session getSession(String key) {
        return webSocketPut.get(key).session;
    }
}
