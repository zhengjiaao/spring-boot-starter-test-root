/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-29 16:42
 * @Since:
 */
package com.zja.springwebsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 在线测试：http://coolaf.com/tool/chattest
 * websocket连接地址：ws://localhost:8080/msg
 */
@Slf4j
@Component
public class MessageJob {

    @Autowired
    WebSocketSendMessagesService webSocketSendMessagesService;

    /**
     * 每5s发送
     */
    @Scheduled(cron = "0/2 * * * * *")
    public void run(){
        try {
            webSocketSendMessagesService.broadcastMsg("服务端消息 "  + LocalDateTime.now().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
