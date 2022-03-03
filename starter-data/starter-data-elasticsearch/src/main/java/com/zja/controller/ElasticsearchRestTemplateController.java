///**
// * @Company: 上海数慧系统技术有限公司
// * @Department: 数据中心
// * @Author: 郑家骜[ào]
// * @Email: zhengja@dist.com.cn
// * @Date: 2022-03-01 15:35
// * @Since:
// */
//package com.zja.controller;
//
//import com.zja.entity.UserEntity;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//@RestController
//@Api(tags = {"BookController"}, description = "Elasticsearch 全文检索")
//public class ElasticsearchRestTemplateController {
//
//    //操作索引
//    @Autowired
//    ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//
//    @ApiOperation(value = "添加索引", httpMethod = "POST")
//    @PostMapping(value = "v1/user/add/{id}")
//    public Object addbook(@ApiParam(defaultValue = "50") @PathVariable String id) {
//        UserEntity u = new UserEntity();
//        u.setId(id);
//        u.setUserName("李四");
//        u.setAge(16);
//        u.setCreateTime(new Date());
//        return elasticsearchRestTemplate.save(u);
//    }
//
//
//}
