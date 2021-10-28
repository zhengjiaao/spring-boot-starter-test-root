/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-28 16:28
 * @Since:
 */
package com.zja.redis.msg;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Redis 消息订阅监听器-接收消息
 */
@Component
public class RedisMsgSubscribeListenerV1 implements MessageListener {

    /**
     * 监听通道
     */
    public final String channel = "channel-1";

    /**
     * 监听方法
     * @param message 消息
     * @param pattern 通道
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        //消息体
        String body = null;
        try {
            //解决string乱码
            body = new String(message.getBody(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String channel = new String(pattern);
        System.out.println("通道：" + channel);
        System.out.println("消息体：" + body);
    }

}
