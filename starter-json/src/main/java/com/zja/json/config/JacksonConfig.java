package com.zja.json.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Jackson 全局配置类
 *
 * @Author: zhengja
 * @Date: 2025-04-14 13:58
 */
@Configuration
public class JacksonConfig {

    // 设置全局 Jackson 配置
    // @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            // 全局日期格式
            // builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
            builder.simpleDateFormat("yyyy-MM-dd");

            // 空值处理 全部返回
            builder.serializationInclusion(JsonInclude.Include.ALWAYS);
            // 空值处理 忽略空值
            // builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);

            // 自定义序列化/反序列化
            // builder.modules(new CustomModule());

            // 忽略未知属性
            builder.failOnUnknownProperties(false);
        };
    }

    // 设置全局 ObjectMapper 配置
    // @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.build();
    }
}