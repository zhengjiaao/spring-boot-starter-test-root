/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-16 15:30
 * @Since:
 */
package com.zja;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest(classes = {com.zja.config.MyConfiguration.class})
//@TestPropertySource("classpath:test.properties")
@SpringBootTest
public class BootTestApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("contextLoadsd");

        int status = 100;
        Assert.assertTrue("错误，正确的返回值为200", status == 200);
    }
}
