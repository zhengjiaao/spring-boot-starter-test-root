//package com.zja.entity;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//import org.springframework.data.elasticsearch.annotations.Setting;
//
///**
// * Company: 上海数慧系统技术有限公司
// * Department: 数据中心
// * Date: 2020-07-17 11:07
// * Author: zhengja
// * Email: zhengja@dist.com.cn
// * Desc：
// * 注解：@Document用来声明Java对象与ElasticSearch索引的关系
// * indexName 索引名称
// * type      索引类型
// * shards    主分区数量，默认5
// * replicas  副本分区数量，默认1
// * createIndex 索引不存在时，是否自动创建索引，默认true
// */
//@Data
//@Document(indexName = "book_index") // 模糊查询的book_index,可以查到book_index开头的所有索引
//@Setting(indexStoreType = "fs", shards = 5, replicas = 1, refreshInterval = "-1")
//public class BookEntity {
//    @Id
//    private String id;
//    //@Field 可选的, 用来描述字段的ES数据类型，是否分词等配置，等于Mapping描述
//    @Field(type = FieldType.Keyword)
//    private String title;
//    @Field(type = FieldType.Text, analyzer = "ik_max_word")
//    private String content;
//    private int userId;
//    private int weight;
//}
