/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-28 16:32
 * @Since:
 */
package com.zja.redis.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis消息发布-发送消息
 */
public class RedisMsgPublish {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发布消息
     * @param channel 通道
     * @param message 消息
     */
    public boolean pubMsg(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
        return true;
    }
}
