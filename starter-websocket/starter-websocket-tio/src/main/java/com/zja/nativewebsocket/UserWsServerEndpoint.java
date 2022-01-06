/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-01 10:24
 * @Since:
 */
package com.zja.nativewebsocket;

import com.zja.base.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket 服务端
 * 在线测试：http://coolaf.com/tool/chattest
 * ws://127.0.0.1:8080/websocket/server/{userId}/{meetingId}
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/server/{userId}/{meetingId}")  // 类似 @RequestMapping
public class UserWsServerEndpoint {

    private static UserService userService;

    @Autowired
    public void setUserMessageService(UserService userService) {
        UserWsServerEndpoint.userService = userService;
    }

    //业务ID：会议id
    private Long meetingId;
    //当前用户id，唯一标识当前连接客户端 例：用户名、用户id等
    private Long userId;
    // 当前聊天对象Session,与某个客户端的连接对话，需要通过它来给客户端发送消息
    private Session session;

    // 未读数量
    private Integer notReadNum;

    /**
     *  用于存所有的连接服务的客户端，这个对象存储是安全的
     */
    private static ConcurrentHashMap<String, UserWsServerEndpoint> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     *
     * @param session
     */
    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("userId") Long userId,
            @PathParam("meetingId") Long meetingId) {
        this.session = session;
        this.userId = userId;
        this.meetingId = meetingId;
        webSocketMap.put(userId + "-" + meetingId, this);
        System.out.println("websocket server Session 连接成功：" + session.getId());
    }

    /**
     * 连接关闭调用的方法
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        webSocketMap.remove(userId + "-" + meetingId);
        System.out.println("websocket server 连接关闭：" + session.getId());
        session.close();
    }

    /**
     * 发生错误时调用
     *
     * @param session
     */
    @OnError
    public void onError(Session session, Throwable error) throws IOException {
        System.out.println("websocket server 异常连接关闭：" + session.getId());
        session.close();
        error.printStackTrace();
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 来自客户端的消息
     * @return 回复给客户端的消息
     */
    @OnMessage
    public String onMessage(String message,
                            Session session, //当前客户端session
                            @PathParam("userId") Long userId,
                            @PathParam("meetingId") Long meetingId) {
        System.out.println("websocket server 来自客户端消息：" + message);

        //发送消息给指定的人
//        this.sendMessageByUserId(message, meetingId, userId);

        /**
         * 回复给客户端消息
         */
        return "websocket server 服务端返回的消息：" + message;
    }


    private UserWsServerEndpoint getUserWsServerEndpoint(Long meetingId, Long userId) {
        return webSocketMap.get(userId + "-" + meetingId);
    }

    /**
     * 根据会议和用户id发送消息
     * @param message
     * @param meetingId
     * @param userId
     */
    public void sendMessageByUserId(String message, Long meetingId, Long userId) {
        UserWsServerEndpoint serverEndpoint = webSocketMap.get(userId + "-" + meetingId);
        if (ObjectUtils.isEmpty(serverEndpoint)) {
            System.out.println("当前用户不在线：" + userId);
            return;
        }
        serverEndpoint.sendMessage(message);
    }

    /**
     * 群发
     * @param message
     */
    public void groupSending(String message) {
        for (String key : webSocketMap.keySet()) {
            try {
                webSocketMap.get(key).session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送消息
     * @param message
     */
    private void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
