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

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 服务端 接口
 */
@WebService(
        name = "UserService",  //可选的，wsdl:portType 暴露服务名称，默认：类的名称+Service
        targetNamespace = "http://service.webservices.zja.com/wsdl" // 命名空间,一般是接口的包名倒序,默认: “http://包名/”
)
public interface UserService {

    String getName(String name);

    UserDTO getUser(Long id);

    @WebMethod( //可选的，搭配 @WebService注解
            operationName = "oKongOperationName", //可选的，默认：Java 方法的名称。指定与此方法相匹配的wsdl:operation 的名称
            action = "oKongAction", //可选的，默认：Java 方法的名称。定义此操作的行为，对于 SOAP 绑定，此值将确定 SOAPAction 头的值
            exclude = false //可选的，默认fasle，是否从 Web Service 中排除某一方法
    )
    String getAuthorName(@WebParam(name = "paramName") String name);
}
