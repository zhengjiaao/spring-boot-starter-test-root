/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-29 13:51
 * @Since:
 */
package com.zja.webservices.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class WebServicesClient {

    /**
     * 客户端的代码就不贴了，直接贴main方法
     * 添加：cxf-spring-boot-starter-jaxws 依赖
     */
    public static void main(String[] args) {
        getName();
        getUser();
    }


    /**
     * 获取用户名
     */
    private static void getName(){
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/webservice/api?wsdl");
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("getName", "李四");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户信息
     */
    private static void getUser(){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/webservice/api?wsdl");
        Object[] objects = new Object[0];
        ObjectMapper mapper = new ObjectMapper();
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("getUser", 99L);
            System.out.println(mapper.writeValueAsString(objects[0]));
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
