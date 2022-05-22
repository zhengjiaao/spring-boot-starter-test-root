/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-05-22 14:24
 * @Since:
 */
package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/swagger-ui/index.html#/
 */
@SpringBootApplication
public class DataJdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataJdbcApplication.class, args);
    }
}
