/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-28 16:23
 * @Since:
 */
package com.zja.redis.config;

import com.zja.redis.msg.RedisMsgPublish;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redis 消息发布配置
 */
@Configuration
public class RedisMsgPublishConfig {

    /**
     * 消息发布者
     */
    @Bean
    public RedisMsgPublish redisMsgPublish() {
        return new RedisMsgPublish();
    }

}
