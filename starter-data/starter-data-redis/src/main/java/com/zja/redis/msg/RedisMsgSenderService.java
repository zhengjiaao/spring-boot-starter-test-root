package com.zja.redis.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: zhengja
 * @since: 2024/04/11 14:01
 */
@Service
public class RedisMsgSenderService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void sendMessage(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }

}
