/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-29 15:57
 * @Since:
 */
package com.zja.webservices.service.wsdl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 */
@SpringBootTest
public class UserServiceTests {

//    @Autowired
//    Client client;

    @Autowired
    @Qualifier("cxfProxy")
    UserService userService;

    @Test
    public void getName() {
        String name = userService.getName("李四");
        System.out.println(name);
    }

    @Test
    public void oKongOperationName() {
        String operationName = userService.oKongOperationName("李四");
        System.out.println(operationName);
    }

    @Test
    public void getUser() {
        UserDTO user = userService.getUser(123L);
        System.out.println(user);
        System.out.println(user.getId());
        System.out.println(user.getAge());
        System.out.println(user.getName());
    }

    /*@GetMapping("/dynamic/{operation}")
    public Object getAuthorStringByDynamic(@PathVariable("operation")String operationName, String authorName) throws Exception {
        //这里就简单的判断了
        Object[] objects = null;
//        client.getEndpoint().getBinding().getBindingInfo().getOperations()
        if ("getAuthorList".equalsIgnoreCase(operationName)) {
            objects = client.invoke(operationName);
        } else if ("getAuthorString".equalsIgnoreCase(operationName)) {
            objects = client.invoke(operationName, authorName);
        } else if ("getAuthorByName".equalsIgnoreCase(operationName)) {
            objects = client.invoke(operationName, authorName);
        } else {
            throw new RuntimeException("无效的调用方法");
        }
        return objects != null && objects.length > 0 ? objects[0] : "返回异常";
    }*/
}
