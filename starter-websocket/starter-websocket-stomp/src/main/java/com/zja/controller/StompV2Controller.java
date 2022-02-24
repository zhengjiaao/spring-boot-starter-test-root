/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-12-13 13:33
 * @Since:
 */
package com.zja.controller;

import com.zja.model.RequestMessage;
import com.zja.model.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @SendTo 与 @SendToUser 是Spring的STOMP协议中注解的标签
 */
@Controller
public class StompV2Controller {

    /**
     * http://localhost:8080/webroot/stomp/v2
     */
    @GetMapping("stomp/v2")
    public String v2() {
        return "stomp-v2.html";
    }

    /**
     * 客户端可以有多种不同的方式，发送消息给server，包括 subscribe和 send
     * @SubscribeMapping("/topic/v2") 标注的方法，只会处理subscribe发送的消息
     * @MessageMapping("/topic/v2") 标注的方法，只会处理send发送的消息
     */
    @MessageMapping("/message")
//    @SubscribeMapping({"/message"})
    @ResponseBody
    public ResponseMessage message(RequestMessage requestMessage) {
        System.out.println("接收消息：" + requestMessage);
        return new ResponseMessage("服务端接收到你发的：" + requestMessage);
    }

    //群发 发送给所有订阅者
    @MessageMapping("/message/v1")
    @SendTo("/topic") // @SendTo 会将接收到的消息发送到指定的路由目的地，所有订阅该消息的用户都能收到，属于广播
    @ResponseBody
    public ResponseMessage messageV1(RequestMessage requestMessage) {
        System.out.println("接收消息：" + requestMessage);
        return new ResponseMessage("服务端接收到你发的：" + requestMessage);
    }

    //指定发送
    @MessageMapping("/message/v2")
    @SendToUser //@SendToUser 消息目的地有UserDestinationMessageHandler来处理，会将消息路由到发送者对应的目的地。默认该注解前缀为/user
//    @SendToUser("/user/message/v2")
    @ResponseBody
    public ResponseMessage messageV2(RequestMessage requestMessage) {
        System.out.println("接收消息：" + requestMessage);
        return new ResponseMessage("服务端接收到你发的：" + requestMessage);
    }

    /**
     * 客户端可以有多种不同的方式，发送消息给server，包括 subscribe和 send
     * @SubscribeMapping("/topic/v2") 标注的方法，只会处理subscribe发送的消息
     * @MessageMapping("/topic/v2") 标注的方法，只会处理send发送的消息
     */
    @MessageMapping("/message/v3")
    @SubscribeMapping({"/message/v3"})
//    @SendToUser("/user/message/v3")
    @ResponseBody
    public ResponseMessage messageV3(RequestMessage requestMessage) {
        System.out.println("接收消息：" + requestMessage);
        return new ResponseMessage("服务端接收到你发的：" + requestMessage);
    }

}
