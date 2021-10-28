/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-26 17:35
 * @Since:
 */
package com.zja.redis.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 */
@SpringBootTest
public class RedisUtilTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test(){
        redisUtil.set("key-test","value-test");
        System.out.println(redisUtil.get("key-test"));
    }
}
