/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-04 15:35
 * @Since:
 */
package com.zja.dao;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class DemoDao {
    public String test(){
        System.out.println("DemoDao test()");
        return "demodao";
    }
}
