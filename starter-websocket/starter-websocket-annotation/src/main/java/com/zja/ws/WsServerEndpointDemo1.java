/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-12-13 15:32
 * @Since:
 */
package com.zja.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket 服务端
 * 在线测试：http://coolaf.com/tool/chattest
 * ws://127.0.0.1:8080/webroot/ws/server/demo1
 */
@Slf4j
@Component
@ServerEndpoint("/ws/server/demo1")  // 类似 @RequestMapping
public class WsServerEndpointDemo1 {

    /**
     *  用于存所有的连接服务的客户端，这个对象存储是安全的
     */
    private static ConcurrentHashMap<String, Session> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("连接成功");
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("连接关闭");
    }

    /**
     * 接收到消息
     *
     * @param text
     */
    @OnMessage
    public String onMessage(String text) throws IOException {
        System.out.println("来自客户端消息：" + text);
        return "servet 发送：" + text;
    }

    /**
     * 错误
     */
    @OnError
    public void onError(Session session, Throwable error) throws IOException {
        error.printStackTrace();
        System.out.println("发生错误");
    }
}
