/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-01 16:09
 * @Since:
 */
package com.zja.nativewebsocket;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.Iterator;
import java.util.Set;

/**
 * http://localhost:8080/swagger-ui/index.html#/
 */
@RestController
public class WsClientEndpointController {

    static String url = "ws://127.0.0.1:8080/websocket/server/v1";

    @ApiOperation(value = "client websocket 连接服务端")
    @GetMapping("/v1/connectToServer")
    public Object connectToServer() throws Exception {
        WebSocketContainer conmtainer = ContainerProvider.getWebSocketContainer();
        conmtainer.connectToServer(WsClientEndpoint.class, URI.create(url));
        return true;
    }

    @ApiOperation(value = "client websocket 关闭客户端")
    @GetMapping("/v1/close")
    public Object close() throws Exception {
        WebSocketContainer conmtainer = ContainerProvider.getWebSocketContainer();
        Session session = conmtainer.connectToServer(WsClientEndpoint.class, URI.create(url));
        System.out.println("测试 sessionid："+session.getId());
        session.close();
        return true;
    }

    @ApiOperation(value = "发送消息")
    @GetMapping("/v2/sendText")
    public Object sendText(@RequestParam String msg) throws Exception {
        WebSocketContainer conmtainer = ContainerProvider.getWebSocketContainer();
        Session session = conmtainer.connectToServer(WsClientEndpoint.class, URI.create(url));
        session.getBasicRemote().sendText(msg);
        session.close();
        return true;
    }

    @ApiOperation(value = "session状态")
    @GetMapping("/v3/isOpen")
    public Object isOpen() throws Exception {
        WebSocketContainer conmtainer = ContainerProvider.getWebSocketContainer();
        Session session = conmtainer.connectToServer(WsClientEndpoint.class, URI.create(url));

        //true在线，false离线
        return session.isOpen();
    }

    @ApiOperation(value = "获取打开的session会话")
    @GetMapping("/v4/getOpenSessions")
    public Object getOpenSessions() throws Exception {
        WebSocketContainer conmtainer = ContainerProvider.getWebSocketContainer();
        Session session = conmtainer.connectToServer(WsClientEndpoint.class, URI.create(url));

        //session 在线列表
        Set<Session> openSessions = session.getOpenSessions();
        System.out.println("session 在线列表 size:" + openSessions.size());
        Iterator<Session> iterator = openSessions.iterator();
        while (iterator.hasNext()) {
            System.out.println("sessionid: " + iterator.next().getId());
        }
        return true;
    }

}
