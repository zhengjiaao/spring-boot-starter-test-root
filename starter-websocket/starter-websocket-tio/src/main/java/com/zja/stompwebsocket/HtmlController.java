/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-12-13 12:46
 * @Since:
 */
package com.zja.stompwebsocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * html 页面访问
 */
@Controller
public class HtmlController {

    /**
     * http://localhost:8080/webroot/stomp/v1
     */
    @GetMapping("stomp/v1")
    public String v1() {
        return "stomp/stomp-v1.html";
    }

    /**
     * http://localhost:8080/webroot/stomp/v2
     */
    @GetMapping("stomp/v2")
    public String v2() {
        return "stomp/stomp-v2.html";
    }
}
