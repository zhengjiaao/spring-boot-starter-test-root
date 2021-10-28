package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching //默认未启用 注解方式缓存 支持：CacheManager 接口
//@EnableRedisRepositories // 默认 启用  CRUD操作将会操作redis中的数据
@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

}
