/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-10 16:30
 * @Since:
 */
package com.zja.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@Table("person")
public class Person implements Serializable {
    @Id
    private Long id;
    @Column("firstname")
    private String firstname;
    private String lastname;
}
