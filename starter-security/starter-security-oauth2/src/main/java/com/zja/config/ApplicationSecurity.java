/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-10 15:52
 * @Since:
 */
package com.zja.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 自定义身份验证管理器
 *
 */
@Configuration
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    /**
     * 常用的帮助程序是AuthenticationManagerBuilder，它非常适合设置内存、JDBC 或 LDAP 用户详细信息或添加自定义UserDetailsService
     * @param builder
     * @param dataSource
     */
    /*@Autowired
    public void initialize(AuthenticationManagerBuilder builder, DataSource dataSource) {
        builder.jdbcAuthentication().dataSource(dataSource).withUser("dave")
                .password("secret").roles("USER");
    }*/
}
