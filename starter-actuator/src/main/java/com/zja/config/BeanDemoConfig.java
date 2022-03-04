/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-03 17:05
 * @Since:
 */
package com.zja.config;

import com.zja.beans.BeanDemo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanDemoConfig {

    @Bean
    BeanDemo beanDemo(){
        return new BeanDemo();
    }

}
