package com.zja.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2021-08-25 10:10
 * Author: zhengja
 * Email: zhengja@dist.com.cn
 * Desc：
 */
@Data
@Node
public class Phone {

    @Id
    @GeneratedValue
    private Long id;
    private String phoneNo;

    /**
     * 一对多的关系
     */
    @Relationship(type = RelsType.BELONG, direction = Relationship.Direction.OUTGOING)
    private List<User> users;
}
