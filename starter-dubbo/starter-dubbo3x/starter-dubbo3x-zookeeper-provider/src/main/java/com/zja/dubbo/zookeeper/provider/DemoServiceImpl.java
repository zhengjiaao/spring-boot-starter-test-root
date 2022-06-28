/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-06-27 13:56
 * @Since:
 */
package com.zja.dubbo.zookeeper.provider;

import com.zja.dubbo.api.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "${demo.service.version}")
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return name + " 你好，您调用 DUBBO RPC 接口成功!";
    }
}
