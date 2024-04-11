/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-28 17:04
 * @Since:
 */
package com.zja.redis.msg;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 测试Redis 发布、订阅
 * 先启动 {@link com.zja.RedisApplication},再进行测试类
 */
@SpringBootTest
public class RedisMsgPublishTests {

    @Autowired
    private RedisMsgPublish redisMsgPublish;

    @Autowired
    RedisMsgSenderService redisMsgSenderService;

    @Test
    public void pubMsg_V1() {
        // redisMsgPublish.pubMsg("channel-1", "channel-1 消息体");

        redisMsgSenderService.sendMessage("channel-1", "channel-1 消息体");
    }

    @Test
    public void pubMsg_V2() {
        // redisMsgPublish.pubMsg("channel-2", "channel-2 消息体");

        redisMsgSenderService.sendMessage("channel-2", "channel-2 消息体");
    }

    @Test
    public void pubMsg_V3() throws JsonProcessingException, UnsupportedEncodingException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId("2");
        userDTO.setName("李四");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser = objectMapper.writeValueAsString(userDTO); // 支持对象、对象集合、Map等转为json字符串
        System.out.println("发送消息: " + jsonUser);

        // redisMsgPublish.pubMsg("channel-3", jsonUser); // todo 不要用，有坑，会转义 或 多加 ""

        redisMsgSenderService.sendMessage("channel-3", jsonUser);
    }
}
