/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-29 15:50
 * @Since:
 */
package com.zja.config;

import com.zja.webservices.service.wsdl.UserService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class CxfClientConfig {

    /**
     * 方式一：代理接口方式调用
     *
     *  以接口代理方式进行调用 UserService接口
     */
    @Bean("cxfProxy")
    public UserService createAuthorPortTypeProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);
        jaxWsProxyFactoryBean.setAddress("http://127.0.0.1:8080/webservice/api?wsdl");

        return (UserService) jaxWsProxyFactoryBean.create();
    }

    /*
     * 采用动态工厂方式 不需要指定服务接口
     */
    @Bean
    public Client createDynamicClient() {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://127.0.0.1:8080/webservice/api?wsdl");
        return client;
    }


    /**
     * 方式二：直接调用
     */
   /* @Bean("jdkProxy")
    public UserService createJdkService() {
        UserService userService = new UserService();
        return userService.oKongOperationName("");
    }*/

}
