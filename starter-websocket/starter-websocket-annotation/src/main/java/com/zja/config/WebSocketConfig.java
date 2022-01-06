/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-01 10:59
 * @Since:
 */
package com.zja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 注解 @EnableWebSocket 启用 websocket
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig {

    /**
     * 将ServerEndpointExporter指定给Spring管理（自动注册使用 @ServerEndpoint 注解）
     *
     * 注意：使用外部Tomcat时,将此 ServerEndpointExporter bean配置注释掉
     * 参考原因：https://cloud.tencent.com/developer/article/1395021?from=15425
     */
    @Bean
    public ServerEndpointExporter serverEndpoint() {
        return new ServerEndpointExporter();
    }
}
