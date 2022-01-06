/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-06 10:40
 * @Since:
 */
package com.zja;

import com.zja.model.MyEvent;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class MyEventClientTest {

    @Test
    public void webClientTest4() {
        Flux<MyEvent> eventFlux = Flux.interval(Duration.ofSeconds(1))
                .map(l -> new MyEvent(System.currentTimeMillis(), "message-" + l)).take(5); // 1    声明速度为每秒一个MyEvent元素的数据流，不加take的话表示无限个元素的数据流；
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient
                .post().uri("/events/post")
                .contentType(MediaType.APPLICATION_STREAM_JSON) // 2    声明请求体的数据格式为application/stream+json；
                .body(eventFlux, MyEvent.class) // 3    body方法设置请求体的数据。
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

}
