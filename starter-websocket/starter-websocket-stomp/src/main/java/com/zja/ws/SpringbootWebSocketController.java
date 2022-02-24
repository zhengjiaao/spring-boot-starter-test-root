package com.zja.ws;

import com.zja.config.WebSocketConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * 在线测试：http://coolaf.com/tool/chattest
 * socket地址：z注解是
 *
 * 访问接口发送消息：http://127.0.0.1:8080/endpointOyzc
 */
@Controller
public class SpringbootWebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    //-----------------定时发送消息--------------------

    /**
     * 广播推送消息
     *
     * 接收地址：http://127.0.0.1:8080/endpointOyzc/topic/broadcast
     */
//    @Scheduled(fixedRate = 5000)
    public void sendTopicMessage() {

        String msg = "hello 你好！";
        System.out.println("后端-广播推送：" + msg);

        this.simpMessagingTemplate.convertAndSend("/topic/broadcast", msg);
    }

    /**
     * 一对一推送消息
     */
//    @Scheduled(fixedRate = 5000)
    public void sendQueueMessage() {
        //消息接收人
        String user = "1";
        String msg = "hello 你好！";
        System.out.println("后端-指定推送：" + msg);
        this.simpMessagingTemplate.convertAndSendToUser(user, "/queue/getData", msg);
    }


    //------------接收消息---------------

    //客户端主动发送消息到服务端，服务端马上回应指定的客户端消息
    //类似http无状态请求，但是有质的区别
    //websocket可以从服务器指定发送哪个客户端，而不像http只能响应请求端

    /**
     * 群聊：此方法用于群发测试
     * 注解 @MessageMapping 类似 @RequestMapping 其中，"/massRequest" 为 {@link WebSocketConfig#configureMessageBroker}方法配置的
     */
    @MessageMapping("/massRequest") //类似 @RequestMapping
    //SendTo 发送至 Broker 下的指定订阅路径
    @SendTo("/mass/getResponse") //把返回值的内容发送给订阅了 /mass/getResponse 的客户端
    public String mass(String msg) {
        System.out.println("群聊-收到消息：" + msg);
        return msg;
    }

    //单独聊天 :方法用于一对一测试
    @MessageMapping("/aloneRequest")
    public String alone(String chatRoomRequest) {
        //消息接收人
        String user = "1";
        String msg = "这是一条消息3";
        this.simpMessagingTemplate.convertAndSendToUser(user, "/alone/getResponse", msg);
        System.out.println("单独-收到消息：" + chatRoomRequest);
        return chatRoomRequest;
    }


//    /**
//     * 表示服务端可以接收客户端通过主题“/app/hello”发送过来的消息，客户端需要在主题"/topic/hello"上监听并接收服务端发回的消息
//     * @param topic
//     * @param headers
//     */
//    @MessageMapping("/hello") //"/hello"为WebSocketConfig类中registerStompEndpoints()方法配置的
//    @SendTo("/topic/greetings")
//    public void greeting(@Header("atytopic") String topic, @Headers Map<String, Object> headers) {
//        System.out.println("connected successfully....");
//        System.out.println(topic);
//        System.out.println(headers);
//    }
//
//    /**
//     * 这里用的是@SendToUser，这就是发送给单一客户端的标志。本例中，
//     * 客户端接收一对一消息的主题应该是“/user/” + 用户Id + “/message” ,这里的用户id可以是一个普通的字符串，只要每个用户端都使用自己的id并且服务端知道每个用户的id就行。
//     * @return
//     */
//    @MessageMapping("/message")
//    @SendToUser("/message")
//    public Greeting handleSubscribe() {
//        System.out.println("this is the @SubscribeMapping('/marco')");
//        return new Greeting("I am a msg from SubscribeMapping('/macro').");
//    }
//
//    /**
//     * 测试对指定用户发送消息方法
//     * @return
//     */
//    @RequestMapping(path = "/send", method = RequestMethod.GET)
//    public Greeting send() {
//        simpMessagingTemplate.convertAndSendToUser("1", "/message", new Greeting("I am a msg from SubscribeMapping('/macro')."));
//        return new Greeting("I am a msg from SubscribeMapping('/macro').");
//    }

}
