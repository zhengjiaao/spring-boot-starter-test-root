/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-06 10:05
 * @Since:
 */
package com.zja;

import com.zja.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

public class UserClientTest {

    @Test
    public void webClientTest1() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8080");   // 1 创建WebClient对象并指定baseUrl；
        Mono<String> resp = webClient
                .get().uri("/user/hello") // 2  HTTP GET；
                .retrieve() // 3    异步地获取response信息；
                .bodyToMono(String.class);  // 4    将response body解析为字符串；
        resp.subscribe(System.out::println);    // 5    打印出来；
        TimeUnit.SECONDS.sleep(1);  // 6    由于是异步的，我们将测试线程sleep 1秒确保拿到response，也可以像前边的例子一样用CountDownLatch。
    }

    @Test
    public void webClientTest2() throws InterruptedException {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build(); // 1    这次我们使用WebClientBuilder来构建WebClient对象
        webClient
                .get().uri("/user/list")
                .accept(MediaType.APPLICATION_STREAM_JSON) // 2 配置请求Header：Content-Type: application/stream+json；
                .exchange() // 3    获取response信息，返回值为ClientResponse，retrive()可以看做是exchange()方法的“快捷版”；
                .flatMapMany(response -> response.bodyToFlux(User.class))   // 4    使用flatMap来将ClientResponse映射为Flux；
                .doOnNext(System.out::println)  // 5    只读地peek每个元素，然后打印出来，它并不是subscribe，所以不会触发流；
                .blockLast();   // 6    上个例子中sleep的方式有点low，blockLast方法，顾名思义，在收到最后一个元素前会阻塞，响应式业务场景中慎用。
    }


}
