package com.zja.undertow.config;

import io.undertow.UndertowOptions;
import org.springframework.boot.autoconfigure.web.embedded.UndertowWebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * @Author: zhengja
 * @Date: 2025-04-15 16:40
 */
@Configuration
public class UndertowConfig {

    // @Bean
    public UndertowServletWebServerFactory undertowServletWebServerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();

        // 线程池配置
        factory.addBuilderCustomizers(builder -> {
            builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true)
                    .setServerOption(UndertowOptions.ALWAYS_SET_KEEP_ALIVE, false)
                    .setWorkerThreads(200)
                    .setIoThreads(Runtime.getRuntime().availableProcessors() * 2);
        });

        // 缓冲区配置
        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            deploymentInfo.setDefaultEncoding(StandardCharsets.UTF_8.name());
        });

        return factory;
    }
}