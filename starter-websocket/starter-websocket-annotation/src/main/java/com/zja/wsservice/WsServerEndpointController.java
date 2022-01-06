/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-01 17:04
 * @Since:
 */
package com.zja.wsservice;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
@RestController
public class WsServerEndpointController {

    @Autowired
    private WsServerEndpoint wsServerEndpoint;

    @ApiOperation(value = "服务端ws session列表")
    @GetMapping("/v1/keys")
    public Object keys() {
        ConcurrentHashMap<String, WsServerEndpoint> webSocket = wsServerEndpoint.getAllWebSocketPut();
        return webSocket.keys();
    }
}
