/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-29 16:35
 * @Since:
 */
package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**跨域演示方案 cross-domain-program
 * swagger：http://localhost:8080/swagger-ui/index.html#/
 * 在线测试：http://coolaf.com/tool/chattest
 * websocket连接地址：ws://localhost:8080/msg
 */
@SpringBootApplication
@EnableScheduling
public class WebSocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
    }
}
