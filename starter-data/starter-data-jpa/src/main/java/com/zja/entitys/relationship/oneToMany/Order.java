/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 14:36
 * @Since:
 */
package com.zja.entitys.relationship.oneToMany;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: zhengja
 * @since: 2023/09/28 14:36
 */
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderNumber;

    private String orderName;  //订单名称

//    @ManyToOne //多对一
//    @JoinColumn(name = "customerNumber")
//    private Customer customer;
}
