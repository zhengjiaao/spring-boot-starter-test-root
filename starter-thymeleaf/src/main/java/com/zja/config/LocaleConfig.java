package com.zja.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.CookieGenerator;

import java.util.Locale;

/**
 * @Author: zhengja
 * @Date: 2025-01-22 14:22
 */
// @Configurable
public class LocaleConfig implements WebMvcConfigurer {

    // 两种自动配置国际化策略，分别是SessionLocaleResolver和CookieLocaleResolver
    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

/*    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieName("myAppLocaleCookie");
        resolver.setCookieMaxAge(48 * 60 * 60); // 设置 Cookie 过期时间为 48 小时
        return resolver;
    }*/

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}