/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-01 10:47
 * @Since:
 */
package com.zja.nativewebsocket;

import com.zja.WebSocketApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

/**
 * 测试客户端连接
 * 首先，启动 {@link WebSocketApplication}
 */
@SpringBootTest
public class WsClientEndpointTests {

    String url = "ws://127.0.0.1:8080/websocket/server/v1";

    @Test
    public void clientTest() throws Exception {
        // Auto-generated method stub
        WebSocketContainer conmtainer = ContainerProvider.getWebSocketContainer();
        Session session = conmtainer.connectToServer(WsClientEndpoint.class, URI.create(url));
        session.getBasicRemote().sendText("测试数据");
    }
}
