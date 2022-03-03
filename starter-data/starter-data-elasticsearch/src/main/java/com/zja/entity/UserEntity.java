///**
// * @Company: 上海数慧系统技术有限公司
// * @Department: 数据中心
// * @Author: 郑家骜[ào]
// * @Email: zhengja@dist.com.cn
// * @Date: 2022-03-01 15:35
// * @Since:
// */
//package com.zja.entity;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Setting;
//
//import java.util.Date;
//
//@Data
//@Document(indexName = "user_index")
//@Setting(indexStoreType = "fs", shards = 5, replicas = 1, refreshInterval = "-1")
//@ApiModel("用户信息实体类")
//public class UserEntity {
//    @Id
//    @ApiModelProperty("唯一标识 ID")
//    private String id;
//    @ApiModelProperty("用户名")
//    private String userName;
//    @ApiModelProperty("年龄")
//    private Integer age;
//    @ApiModelProperty("创建时间")
//    private Date createTime;
//}
