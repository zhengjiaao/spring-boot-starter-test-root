package com.zja.mustache.config;

import com.samskivert.mustache.Mustache;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * @Author: zhengja
 * @Date: 2025-04-15 15:54
 */
@Configuration
public class MustacheConfig {
    // @Bean
    public MustacheViewResolver mustacheViewResolver(
            Mustache.Compiler compiler) {

        MustacheViewResolver resolver = new MustacheViewResolver(compiler);
        resolver.setOrder(0);
        resolver.setCharset(StandardCharsets.UTF_8.name());
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        return resolver;
    }
}