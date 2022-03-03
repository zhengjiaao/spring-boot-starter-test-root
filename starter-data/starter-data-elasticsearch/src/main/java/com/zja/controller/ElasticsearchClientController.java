///**
// * @Company: 上海数慧系统技术有限公司
// * @Department: 数据中心
// * @Author: 郑家骜[ào]
// * @Email: zhengja@dist.com.cn
// * @Date: 2022-03-03 16:26
// * @Since:
// */
//package com.zja.controller;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch.core.SearchResponse;
//import co.elastic.clients.elasticsearch.core.search.Hit;
//import co.elastic.clients.elasticsearch.indices.Alias;
//import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
//import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
//import com.zja.entity.Product;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@Slf4j
//@RestController
//@RequestMapping("/es/client")
//public class ElasticsearchClientController {
//
//    @Autowired
//    ElasticsearchClient client;
//
//    @GetMapping("/create")
//    public Object create() throws IOException {
//        CreateIndexResponse indexResponse = client.indices().create(c -> c.index("products"));
//        System.out.println(indexResponse);
//        return true;
//    }
//
//    @GetMapping("/create/v2")
//    public Object create2() throws IOException {
//        CreateIndexResponse createResponse = client.indices().create(
//                new CreateIndexRequest.Builder()
//                        .index("my-index")
//                        .aliases("foo",
//                                new Alias.Builder().isWriteIndex(true).build()
//                        )
//                        .build()
//        );
//        return true;
//    }
//
//    @GetMapping("/create/v3")
//    public Object create3() throws IOException {
//        CreateIndexResponse createResponse = client.indices()
//                .create(createIndexBuilder -> createIndexBuilder
//                        .index("my-index")
//                        .aliases("foo", aliasBuilder -> aliasBuilder
//                                .isWriteIndex(true)
//                        )
//                );
//        return true;
//    }
//
//    @GetMapping("/search")
//    public Object search() throws IOException {
//        SearchResponse<Product> search = client.search(s -> s
//                        .index("products")
//                        .query(q -> q
//                                .term(t -> t
//                                        .field("name")
//                                        .value(v -> v.stringValue("bicycle"))
//                                )),
//                Product.class);
//
//        for (Hit<Product> hit : search.hits().hits()) {
//            System.out.println(hit.source());
//        }
//        return true;
//    }
//
//}
