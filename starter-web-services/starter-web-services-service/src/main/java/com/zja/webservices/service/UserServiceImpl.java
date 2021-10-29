/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-29 13:43
 * @Since:
 */
package com.zja.webservices.service;

import com.zja.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * 服务端 实现类
 */
@Service
@WebService(
        serviceName = "UserService", // 与接口中指定的name一致，对外发布的服务名，缺省值：类+Service
        targetNamespace = "http://service.webservices.zja.com/wsdl",            // 与接口中的命名空间一致
        endpointInterface = "com.zja.webservices.service.UserService",   // 接口地址
        portName = "UserportName"  //可选的，wsdl:portName的值 ,缺省值: WebService.name+Port
//        wsdlLocation = ""          //可选的，指定用于定义 Web Service 的 WSDL 文档的 Web 地址
)
public class UserServiceImpl implements UserService {

    @Override
    public String getName(String name) {

        return "查询的名字为: " + name;
    }

    @Override
    public UserDTO getUser(Long id) {
        return UserDTO.builder().id(id).name("李四").build();
    }

    @Override
    public String getAuthorName(String name) {

        return "查询的名字为: " + name;
    }
}
