/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 10:53
 * @Since:
 */
package com.zja.entitys.listener;

import com.zja.entitys.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author: zhengja
 * @since: 2023/09/28 10:53
 */
@Slf4j
@Getter
@Setter
@Entity
@Table(name = "jpa_order")
//@EntityListeners(value = OrderEntityListener.class) // 添加订单实体类的监听器
public class OrderEntity extends BaseEntity {

    @Column(nullable = false)
    private int orderStatus;

    @Column(nullable = false)
    private BigDecimal price;

    /*
     * 以下方法也可以写在自定义监听类中 OrderEntityListener
     */
    @PrePersist
    public void prePersist() {
        this.setOrderStatus(1);
        log.info("orderId: {}，status :{},新增之前修改订单状态为 1", this.getId(), this.getOrderStatus());
    }

    @PostPersist
    public void postPersist() {
        log.info("orderId: {}，status :{},新增之后，异步通知仓库进行处理", this.getId(), this.getOrderStatus());
    }

    @PostLoad
    public void postLoad() {
        log.info("orderId: {}，status :{},加载之后...", this.getId(), this.getOrderStatus());
    }

    @PreUpdate
    public void preUpdate() {
        log.info("orderId: {}，status :{},修改之前.....", this.getId(), this.getOrderStatus());
    }

    @PostUpdate
    public void postUpdate() {
        log.info("orderId: {}，status :{},修改之后根据订单状态进行不同的判断", this.getId(), this.getOrderStatus());
    }

    @PreRemove
    public void preRemove() {
        log.info("orderId: {}，status :{},删除之前.....", this.getId(), this.getOrderStatus());
    }

    @PostRemove
    public void postRemove() {
        log.info("orderId: {}，status :{},删除之后.....", this.getId(), this.getOrderStatus());
    }

}
