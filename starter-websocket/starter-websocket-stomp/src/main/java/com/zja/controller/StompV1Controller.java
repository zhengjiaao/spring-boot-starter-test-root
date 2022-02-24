/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-12-14 11:01
 * @Since:
 */
package com.zja.controller;

import com.zja.model.RequestMessage;
import com.zja.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StompV1Controller {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * http://localhost:8080/webroot/stomp/v1
     */
    @GetMapping("stomp/v1")
    public String test() {
        return "stomp-v1.html";
    }

    /**
     * 注解 @MessageMapping 客户端发送的消息地址  示例：stompClient.send("/app/hello", {}, JSON.stringify({'content': content}));
     * 注解 @SendTo   客户端订阅地址
     *
     * @param requestMessage 客户端发送的消息
     * @return 返回给客户端消息信息
     */
    @MessageMapping("/hello")
    @SendTo("/topic/hello")     //类似 SimpMessagingTemplate Send 中 destination 值
    @ResponseBody
    public ResponseMessage hello(RequestMessage requestMessage) {
        System.out.println("接收消息：" + requestMessage);
        return new ResponseMessage("服务端接收到你发的：" + requestMessage);
    }

    /**
     * 服务端 发送消息 给指定用户
     */
    @GetMapping("/sendMsgByUser")
    @ResponseBody
    public Object sendMsgByUser(String user, String msg) {
        simpMessagingTemplate.convertAndSendToUser(user, "/msg", msg);
        return "success";
    }

    /**
     * 服务端 发送消息 给所有订阅的用户
     */
    @GetMapping("/sendMsgByAll")
    @ResponseBody
    public Object sendMsgByAll(String msg) {
        simpMessagingTemplate.convertAndSend("/topic", msg);
        return "success";
    }

}
