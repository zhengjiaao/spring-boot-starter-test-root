package com.zja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 浏览器访问-服务端：http://localhost:8080/webservice
 */
@SpringBootApplication
public class WebServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServicesApplication.class, args);

        //Application mian 方法启动 服务端
     /*   String url = "http://localhost:8082/webservice";
        Endpoint.publish(url,new BillServiceImpl());
        System.out.println("发布webService成功！");*/
    }

}
