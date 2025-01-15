package com.zja.jpa.lazy.maven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 启动类
 * @swagger: <a href="http://localhost:8080/swagger-ui/index.html">...</a>
 * @author: zhengja
 * @since: 2024/09/24 17:36
 */
@EnableJpaAuditing
@SpringBootApplication
public class JpaLazyMavenApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JpaLazyMavenApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JpaLazyMavenApplication.class);
    }
}