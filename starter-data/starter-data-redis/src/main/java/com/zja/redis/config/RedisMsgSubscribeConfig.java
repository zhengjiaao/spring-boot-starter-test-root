/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-28 16:24
 * @Since:
 */
package com.zja.redis.config;

import com.zja.redis.msg.RedisMsgSubscribeListenerV1;
import com.zja.redis.msg.RedisMsgSubscribeListenerV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Redis 消息队列-订阅者(监听)配置
 */
@Configuration
public class RedisMsgSubscribeConfig {

    @Autowired
    private RedisMsgSubscribeListenerV1 redisMsgSubscribeListenerV1;

    @Autowired
    private RedisMsgSubscribeListenerV2 redisMsgSubscribeListenerV2;

    /**
     * Redis 消息侦听器容器（工厂）
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        //设置运行任务的线程池
        container.setTaskExecutor(initTaskScheduler());
        /**
         * 自定义监听 器监听Redis的消息
         * ChannelTopic：定义监听通道 channel
         */
        container.addMessageListener(redisMsgSubscribeListenerV1, new ChannelTopic(redisMsgSubscribeListenerV1.channel));
        container.addMessageListener(redisMsgSubscribeListenerV2, new ChannelTopic(redisMsgSubscribeListenerV2.channel));
        return container;
    }

    //任务池
    private ThreadPoolTaskScheduler taskScheduler;

    /**
     * 创建任务池，运行线程等待处理redis的消息
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler() {
        if (null != taskScheduler) {
            return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

}
