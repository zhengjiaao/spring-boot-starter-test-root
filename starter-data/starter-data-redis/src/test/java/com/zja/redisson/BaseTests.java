package com.zja.redisson;

import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.RedissonRxClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

//@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseTests {

    @Resource
    protected RedissonClient redisson;

    @Resource
    protected RedissonReactiveClient redissonReactiveClient;

    @Resource
    protected RedissonRxClient redissonRxClient;

}
