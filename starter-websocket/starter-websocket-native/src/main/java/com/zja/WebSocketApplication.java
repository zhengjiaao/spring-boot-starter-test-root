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

/**
 * swagger：http://localhost:8080/webroot/swagger-ui/index.html#/
 * 在线测试：http://coolaf.com/tool/chattest
 * websocket连接地址：ws://localhost:8080/webroot/ws/auth/msg?token=1
 * SockJS连接地址：http://localhost:8080/webroot/ws/auth/msg?token=1
 *
 * SockJS 所处理的 URL 是 “http://“ 或 “https://“ 模式，而不是 “ws://“ or “wss://“；
 */
@SpringBootApplication
public class WebSocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
    }
}
