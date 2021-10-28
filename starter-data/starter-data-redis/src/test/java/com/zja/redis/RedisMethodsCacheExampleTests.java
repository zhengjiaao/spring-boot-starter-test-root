/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-27 14:13
 * @Since:
 */
package com.zja.redis;

import com.zja.redis.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 */
@SpringBootTest
public class RedisMethodsCacheExampleTests {

    @Autowired
    private RedisMethodsCacheExample redisMethodsCacheExample;

    private static String parameter ="id123";

    @Test
    public void findData() {
        System.out.println(redisMethodsCacheExample.findData());
    }

    @Test
    public void findDataV1() {
        System.out.println(redisMethodsCacheExample.findData(parameter));
    }

    @Test
    public void findDataValueIsNull() {
        System.out.println(redisMethodsCacheExample.findDataValueIsNull());
    }

    @Test
    public void findAllData() {
        System.out.println(redisMethodsCacheExample.findAllData(1, 5));
    }

    @Test
    public void saveData() {
        System.out.println(redisMethodsCacheExample.saveData(parameter));
    }

    @Test
    public void putData() {
        System.out.println(redisMethodsCacheExample.putData(parameter));
    }

    @Test
    public void deleteData() {
        redisMethodsCacheExample.deleteData(parameter);
    }

    @Test
    public void deleteAllData() {
        redisMethodsCacheExample.deleteAllData();
    }

    @Test
    public void saveObject() {
        redisMethodsCacheExample.saveObject(UserEntity.builder().name("李四").build());
    }


    //自定义缓存解析规则，支持设置过期时间
    @Test
    public void customizeCacheNames() {
        redisMethodsCacheExample.customizeCacheNames();
    }


}
