/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-14 11:03
 * @Since:
 */
package com.zja.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "one_entity")
public class OneEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String uid;
    private String name;
    private Date createTime;
}
