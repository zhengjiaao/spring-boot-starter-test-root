package com.zja.redisson.config;

import com.zja.redisson.codec.FastjsonCodec;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.RedissonRxClient;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redis 服务端版本 安装要求 5.0以上
 */
@Configuration
public class RedissonConfig {

    /**
     * Redisson  对应 redis服务端5.0以上版本
     */
    final String address = "redis://127.0.0.1:6379";
    final int database = 0;

    //Redisson 原生(支持异步)客户端
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(address)
                .setDatabase(database);
//                .setAddress("redis://localhost:6379");

        //默认JsonJackson json序列化方式  x1Ap\x00\x00\x00\x01
        //Codec codec = new JsonJacksonCodec();

        //自定义序列化方式 "a": 1
        Codec codec = new FastjsonCodec();
        config.setCodec(codec);

        //默认 connects to 127.0.0.1:6379
//        return Redisson.create();
        return Redisson.create(config);
    }

    //Redisson 反应式客户端
    @Bean
    public RedissonReactiveClient redissonReactiveClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(address);
//                .setAddress("redis://localhost:6379");

        return Redisson.createReactive(config);
    }

    //Redisson RxJava2 客户端
    @Bean
    public RedissonRxClient redissonRxClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(address);
//                .setAddress("redis://localhost:6379");

        return Redisson.createRx(config);
    }


}
