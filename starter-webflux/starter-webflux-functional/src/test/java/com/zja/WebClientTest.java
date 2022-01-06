/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-06 10:11
 * @Since:
 */
package com.zja;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientTest {
    @Test
    public void webClientTest3() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient
                .get().uri("/times")
                .accept(MediaType.TEXT_EVENT_STREAM)    // 1    配置请求Header：Content-Type: text/event-stream，即SSE；
                .retrieve()
                .bodyToFlux(String.class)
                .log()  // 2    这次用log()代替doOnNext(System.out::println)来查看每个元素；
                .take(10)   // 3    由于/times是一个无限流，这里取前10个，会导致流被取消；
                .blockLast();
    }
}
