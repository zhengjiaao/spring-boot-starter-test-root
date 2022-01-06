/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-12-13 16:48
 * @Since:
 */
package com.zja.controller;

import com.zja.ws.WsSessionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@RestController
public class AuthController {

    @GetMapping("/sendMsg")
    public Object sendMsg(String token, String msg) throws IOException {
        WebSocketSession webSocketSession = WsSessionManager.get(token);
        if (webSocketSession == null) {
            return "用户登录已失效";
        }
        webSocketSession.sendMessage(new TextMessage(msg));
        return "消息发送成功";
    }

}
