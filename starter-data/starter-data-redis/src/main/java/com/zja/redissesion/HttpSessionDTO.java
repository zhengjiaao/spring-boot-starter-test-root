///**
// * @Company: 上海数慧系统技术有限公司
// * @Department: 数据中心
// * @Author: 郑家骜[ào]
// * @Email: zhengja@dist.com.cn
// * @Date: 2021-11-23 13:06
// * @Since:
// */
//package com.zja.redissesion;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Builder;
//import lombok.Data;
//
//import java.io.Serializable;
//
///**
// *
// */
//@Data
//@Builder
//public class HttpSessionDTO implements Serializable {
//    private String sessionId;
//    private String attributeName;
//    private Integer attributeAge;
//    //以秒为单位，即在没有活动30分钟后，session将失效
//    private int maxInactiveInterval;
//    //上次访问时间
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private long lastAccessedTime;
//}
