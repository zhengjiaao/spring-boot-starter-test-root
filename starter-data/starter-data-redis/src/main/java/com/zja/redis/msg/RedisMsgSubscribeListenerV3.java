package com.zja.redis.msg;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Redis 消息订阅监听器-接收消息
 */
@Component
public class RedisMsgSubscribeListenerV3 implements MessageListener {

    /**
     * 监听通道
     */
    public final String channel = "channel-3";

    /**
     * 监听方法
     *
     * @param message 消息
     * @param pattern 通道
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {

        // 消息体
        String jsonUser = null;
        try {
            // 解决string乱码
            jsonUser = new String(message.getBody(), StandardCharsets.UTF_8);

            String channel = new String(pattern);
            System.out.println("通道：" + channel);
            System.out.println("消息体：" + jsonUser);

            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(jsonUser, User.class);
            System.out.println("user:" + user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class User {
        private String id;
        private String name;
    }

}
