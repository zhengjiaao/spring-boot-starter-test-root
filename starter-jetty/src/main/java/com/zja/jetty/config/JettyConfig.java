package com.zja.jetty.config;

import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhengja
 * @Date: 2025-04-15 16:31
 */
@Configuration
public class JettyConfig {

    // @Bean
    public JettyServerCustomizer jettyServerCustomizer() {
        return server -> {
            // 配置线程池
            final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
            threadPool.setMaxThreads(200);
            threadPool.setMinThreads(20);
            threadPool.setIdleTimeout(60000);

            // 其他服务器配置
            server.setAttribute("org.eclipse.jetty.server.Request.maxFormContentSize", 2000000);
        };
    }
}