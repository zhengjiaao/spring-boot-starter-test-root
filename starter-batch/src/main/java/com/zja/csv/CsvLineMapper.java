///**
// * @Company: 上海数慧系统技术有限公司
// * @Department: 数据中心
// * @Author: 郑家骜[ào]
// * @Email: zhengja@dist.com.cn
// * @Date: 2022-11-24 17:16
// * @Since:
// */
//package com.zja.csv;
//
//import com.zja.entity.User;
//import org.springframework.batch.item.file.LineMapper;
//
//public class CsvLineMapper implements LineMapper {
//
//    @Override
//    public Object mapLine(String line, int lineNumber) throws Exception {
//        String[] lines = line.split(",");
//        return new User(lines[0], lines[1], lines[2]);
//    }
//}
