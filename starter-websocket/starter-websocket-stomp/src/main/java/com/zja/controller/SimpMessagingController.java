/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-06 13:06
 * @Since:
 */
package com.zja.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 在线测试：http://coolaf.com/tool/chattest
 * socket地址：
 */
@Controller
@RequestMapping("/webSocket")
@MessageMapping("demo")
public class SimpMessagingController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    //广播地址
    public static String send_topic = "/topic/data";
    //指定用户地址
    public static String send_user = "/user/data";

    @GetMapping("/sendTopicMessage")
    @ApiOperation("广播消息")
    public void sendTopicMessage() {
        String msg = "hello 你好！";
        System.out.println("后端-广播推送：" + msg);
        /**
         * destination 消息推送目的地
         * payload 消息体
         */
        this.simpMessagingTemplate.convertAndSend(send_topic, msg);
    }

    @GetMapping("/sendQueueMessage")
    @ApiOperation("指定推送消息")
    public void sendQueueMessage(@ApiParam("消息接收者") String user) {
        String msg = "hello 你好！";
        System.out.println("后端-指定推送 [" + user + "]：" + msg);
        /**
         * user 消息接收者
         * destination 消息推送目的地
         * payload 消息体
         */
        this.simpMessagingTemplate.convertAndSendToUser(user, send_user, msg);
    }


    /**
     * 群发
     * @MessageMapping 接收客户端发送消息的路径
     * @return String 把返回值发送给订阅 @SendTo value 路径的客户端
     */
    @MessageMapping("/sendToTopic") //类似 @RequestMapping
    @SendTo(value = "/topic/data")
    public String topic() {
        return "广播推送 - Hello 你好！";
    }

    /**
     * 单独发送
     * @MessageMapping 接收客户端发送消息的路径
     * @return String 把返回值发送给订阅 @SendToUser value 路径的客户端
     */
    @MessageMapping("/sendToUser") //类似 @RequestMapping
//    @SendToUser // 默认 /user
    @SendToUser(value = {"/user/data"}/*, broadcast = false*/) // broadcast = false把避免推送到所有的session中
    public String user(String msg) {
        System.out.println("群聊-收到消息：" + msg);
        return msg;
    }

}
