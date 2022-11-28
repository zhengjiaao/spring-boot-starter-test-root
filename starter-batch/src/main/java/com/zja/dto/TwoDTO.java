/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-11-28 13:20
 * @Since:
 */
package com.zja.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TwoDTO {
    private String uid;
    private String name;
    private Date createTime;
}
