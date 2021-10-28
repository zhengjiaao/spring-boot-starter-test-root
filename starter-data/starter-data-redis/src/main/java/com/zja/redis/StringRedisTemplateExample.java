/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-26 15:37
 * @Since:
 */
package com.zja.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * StringRedisTemplate 示例(用法参考：RedisTemplate)
 *
 * stringRedisTemplate.opsForValue()  操作字符串
 * stringRedisTemplate.opsForHash()   操作hash
 * stringRedisTemplate.opsForList()   操作list
 * stringRedisTemplate.opsForSet()    操作set
 * stringRedisTemplate.opsForZSet()   操作有序set
 *
 * stringRedisTemplate.opsForGeo()
 * stringRedisTemplate.opsForStream()
 * stringRedisTemplate.opsForCluster()
 * stringRedisTemplate.opsForHyperLogLog()
 *
 * StringRedisTemplate 使用 StringRedisSerializer 序列化
 * StringRedisTemplate 默认序列化 操作字符串时，不会乱码
 */
@Component
public class StringRedisTemplateExample {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * StringRedisTemplate 用法
     * 用法参考：RedisTemplate
     */
    private void stringRedisTemplateTest() {
        stringRedisTemplate.opsForValue().set("str-key", "str-value");
        stringRedisTemplate.opsForValue().get("str-key");
    }
}
