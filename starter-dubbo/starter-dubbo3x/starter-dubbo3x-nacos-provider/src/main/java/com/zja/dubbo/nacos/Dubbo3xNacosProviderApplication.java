/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-06-27 16:01
 * @Since:
 */
package com.zja.dubbo.nacos;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo(scanBasePackages = {"com.zja.dubbo.nacos.provider"})
@SpringBootApplication
public class Dubbo3xNacosProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo3xNacosProviderApplication.class, args);
    }

}
