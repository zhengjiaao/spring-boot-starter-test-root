/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-31 16:26
 * @Since:
 */
package com.zja.redissesion;

import org.springframework.session.FlushMode;
import org.springframework.session.SaveMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 启动 RedisHttpSession
 */
@EnableRedisHttpSession(
        maxInactiveIntervalInSeconds = 1800,    // 会话超时（以秒为单位）。默认情况下，它设置为 1800 秒（30 分钟）。 这应该是一个非负整数
        redisNamespace = "spring:session",      // Redis 键的唯一命名空间，多个应用可以用在同一个redis中
        flushMode = FlushMode.ON_SAVE,          // 刷新模式 ON_SAVE在return后才保持session到redis，IMMEDIATE在retrun前保存session到redis
        cleanupCron = "0 * * * * *",            // 过期会话清理作业的 cron 表达式。默认情况下每分钟运行一次。 * @return 会话清理 cron 表达式
        saveMode = SaveMode.ON_SET_ATTRIBUTE    // 会话的保存模式。默认值为 {@link SaveMode#ON_SET_ATTRIBUTE}，* 仅保存对会话所做的更改。
)
public class RedisHttpSessionConfig {

   /* @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }*/

}
