/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-10 10:19
 * @Since:
 */
package com.zja.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data        //lombok插件
@Component
@PropertySource("classpath:resource.properties")
@ConfigurationProperties(prefix = "server")
public class ServerSetttings {
    //服务器名称
    private String name;
    //接口域名
    private String domain;
}
