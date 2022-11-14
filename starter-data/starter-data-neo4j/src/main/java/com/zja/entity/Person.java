package com.zja.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

import java.io.Serializable;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2021-08-25 9:10
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：Neo4j 默认存在
 */
@Data
@Node
public class Person implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
