/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-01 14:13
 * @Since:
 */
package com.zja.ws;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@RestController
public class UserWsServerEndpointController {

    @Autowired
    private UserWsServerEndpoint userWsServerEndpoint;

    @ApiOperation("指定人发送消息")
    @GetMapping("/send/by/{message}/{userId}/{meetingId}")
    public Object sendMessageByUserId(@ApiParam("消息体") @PathVariable String message,
                                      @ApiParam("用户id") @PathVariable Long userId,
                                      @ApiParam("业务id-会议id") @PathVariable Long meetingId) {
        userWsServerEndpoint.sendMessageByUserId(message, meetingId, userId);
        return true;
    }

    @ApiOperation(value = "发送消息给所有连接用户")
    @GetMapping("/send/all/{message}")
    public Object groupSending(String message) {
        userWsServerEndpoint.groupSending(message);
        return true;
    }

    @ApiOperation(value = "在线用户列表")
    @GetMapping("/user/list")
    public Object groupSending() {
        ConcurrentHashMap<String, UserWsServerEndpoint> webSocketMap = userWsServerEndpoint.getWebSocketMap();
        return webSocketMap.keys();
    }

    @ApiOperation(value = "在线用户数")
    @GetMapping("/user/online")
    public Object online() {
        ConcurrentHashMap<String, UserWsServerEndpoint> webSocketMap = userWsServerEndpoint.getWebSocketMap();
        return webSocketMap.size();
    }

}
