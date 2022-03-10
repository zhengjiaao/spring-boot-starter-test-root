/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-10 16:18
 * @Since:
 */
package com.zja.runners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@Component
public class JdbcRunner implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        log.info("dataSource:{}", dataSource.getClass().getName());
        Connection connection = dataSource.getConnection();
        log.info("connection:{}", connection.toString());
    }

}
