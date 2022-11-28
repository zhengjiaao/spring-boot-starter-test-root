/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-11-25 16:34
 * @Since:
 */
package com.zja.jta;

import com.zja.jta.primary.PrimaryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class PrimaryEntityServiceTests {

    @Resource
    PrimaryService primaryService;

    //正常录入数据
    @Test
    public void add_example_test() {
        primaryService.add_example();
    }

    //异常测试回滚
    @Test
    public void add_rollback_example_test() {
        primaryService.add_rollback_example();
    }
}
