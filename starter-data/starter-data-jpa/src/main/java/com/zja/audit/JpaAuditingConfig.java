/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-27 17:24
 * @Since:
 */
package com.zja.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author: zhengja
 * @since: 2023/09/27 17:24
 */
@Configuration
//@EnableJpaAuditing
@EnableJpaAuditing(auditorAwareRef = "auditorProviderB") //若注册了多个实现，则通过 auditorAwareRef=“”进行选择
public class JpaAuditingConfig {

    @Bean("auditorProviderA")
    public AuditorAware<String> auditorProviderA() {
        return new AuditorAwareImplA();
    }

    @Bean("auditorProviderB")
    public AuditorAware<String> auditorProviderB() {
        return new AuditorAwareImplB();
    }

}
