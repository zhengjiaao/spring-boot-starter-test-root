/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-11 9:51
 * @Since:
 */
package com.zja;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/swagger-ui/index.html#/
 *
 * 要实现多Job的情况，需要把EnableBatchProcessing注解的modular设置为true，让每个Job使用自己的ApplicationConext
 */
@SpringBootApplication
@EnableBatchProcessing(modular = false) // 开启Spring Batch支持
public class BatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

}
