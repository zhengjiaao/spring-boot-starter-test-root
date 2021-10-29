/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-29 14:11
 * @Since:
 */
package com.zja.config;

import com.zja.webservices.service.UserService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * cxf配置类
 *
 * springboot + webserbice两种方式cxf和jax-ws
 *
 * 最常用的是使用cxf开发web-service，本身CXF也实现了JAX-RS规范来实现RESTFul Service
 */
@Configuration
public class CxfWebServiceConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private UserService userService;

    /**
     * 改变项目中服务名的前缀名
     * 注入Servlet 注意beanName不能为 dispatcherServlet
     * 启动服务-访问：http://localhost:8080/webservice
     *
     * 也可以不设置此bean 直接通过配置项 cxf.path 来修改访问路径的
     */
    @Bean
    public ServletRegistrationBean cxfServlet() {
        //注册servlet 拦截/ws 开头的请求 不设置 默认为：/services/*
        return new ServletRegistrationBean(new CXFServlet(), "/webservice/*");
    }

    /**
     * 非必要项
     */
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    /**
     * jax-ws : 发布 endpoint
     */
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), userService);
        endpoint.publish("/api"); //发布地址
        return endpoint;
    }
}
