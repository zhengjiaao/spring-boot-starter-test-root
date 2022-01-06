/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-12-13 13:33
 * @Since:
 */
package com.zja.stompwebsocket;

import com.zja.stompwebsocket.model.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

//使用 @SendTo 注解
@Controller
public class StompV2Controller {

    //群发
    @MessageMapping("/message/v1")
    @SendTo("/topic/v1")
    public ResponseMessage hello(RequestMessage requestMessage) {
        System.out.println("接收消息：" + requestMessage);
        return new ResponseMessage("服务端接收到你发的：" + requestMessage);
    }

    //指定发送
    @MessageMapping("/sendToUser")
    @SendToUser("/user/message")
    public ResponseMessage message(RequestMessage requestMessage) {
        System.out.println("接收消息：" + requestMessage);
        return new ResponseMessage("服务端接收到你发的：" + requestMessage);
    }

}
