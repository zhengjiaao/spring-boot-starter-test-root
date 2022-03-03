///**
// * @Company: 上海数慧系统技术有限公司
// * @Department: 数据中心
// * @Author: 郑家骜[ào]
// * @Email: zhengja@dist.com.cn
// * @Date: 2022-03-03 16:24
// * @Since:
// */
//package com.zja.config;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ElasticsearchClientsJavaConfig {
//
//    /**
//     *  elasticsearch-java 客户端
//     */
//    @Bean
//    public ElasticsearchClient elasticsearchClient() {
//        // Create the low-level client
//        RestClient restClient = RestClient.builder(
//                new HttpHost("localhost", 9200)).build();
//
//        // Create the transport with a Jackson mapper
//        ElasticsearchTransport transport = new RestClientTransport(
//                restClient, new JacksonJsonpMapper());
//
//        // And create the API client
//        ElasticsearchClient client = new ElasticsearchClient(transport);
//        return client;
//    }
//
//}
