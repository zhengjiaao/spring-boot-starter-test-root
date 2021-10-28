/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-26 15:48
 * @Since:
 */
package com.zja.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 *
 */
@SpringBootTest
public class StringRedisTemplateTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void opsForValue_Set_V1() {
        stringRedisTemplate.opsForValue().set("str-key","123，你好呀！");
        Object o = stringRedisTemplate.opsForValue().get("str-key");
        System.out.println(o);
    }
}
