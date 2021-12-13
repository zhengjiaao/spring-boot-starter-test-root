/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-23 10:13
 * @Since:
 */
package com.zja.redissesion;

import org.springframework.context.annotation.Configuration;

/**
 * Http 会话策略配置: 支持cookie、header 存储sessionID，默认使用cookie策略
 * 浏览器：一般使用cookie，手机app应用等不支持cookie，只能使用cookie
 */
@Configuration
public class HttpSessionStrategyConfig {

//    /**
//     * 使用cookie存储sessionID, 默认
//     */
//    @Bean
//    public CookieHttpSessionStrategy cookieHttpSessionStrategy() {
//        return new CookieHttpSessionStrategy();
//    }

//    /**
//     * 使用header存储sessionID
//     */
//    @Bean
//    public HeaderHttpSessionStrategy headerHttpSessionStrategy() {
//        return new HeaderHttpSessionStrategy();
//    }

    /**
     * 自定义缓存策略:同时支持header和cookie存储sessionID
     *
     * 浏览器：一般使用cookie
     * 手机app：仅支持header，不支持cookie
     */
    /*@Bean
    public HeaderAndCookieHttpSessionStrategy headerAndCookieHttpSessionStrategy(){
        return new HeaderAndCookieHttpSessionStrategy();
    }*/
}
