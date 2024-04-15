/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 10:53
 * @Since:
 */
package com.zja.entitys.listener;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * 订单实体监听类
 *
 * @author: zhengja
 * @since: 2023/09/28 10:53
 */
@Slf4j
public class OrderEntityListener {

    @PrePersist
    public void prePersist(OrderEntity order) {
        order.setOrderStatus(1);
        log.info("orderId: {}，status :{},新增之前修改订单状态为 1", order.getId(), order.getOrderStatus());
    }

    @PostPersist
    public void postPersist(OrderEntity order) {
        log.info("orderId: {}，status :{},新增之后，异步通知厂库进行处理", order.getId(), order.getOrderStatus());
    }

    @PostLoad
    public void postLoad(OrderEntity order) {
        log.info("orderId: {}，status :{},加载之后...", order.getId(), order.getOrderStatus());
    }

    @PreUpdate
    public void preUpdate(OrderEntity order) {
        log.info("orderId: {}，status :{},修改之前.....", order.getId(), order.getOrderStatus());
    }

    @PostUpdate
    public void postUpdate(OrderEntity order) {
        log.info("orderId: {}，status :{},修改之后根据订单状态进行不同的判断", order.getId(), order.getOrderStatus());
    }

    @PreRemove
    public void preRemove(OrderEntity order) {
        log.info("orderId: {}，status :{},删除之前.....", order.getId(), order.getOrderStatus());
    }

    @PostRemove
    public void postRemove(OrderEntity order) {
        log.info("orderId: {}，status :{},删除之后.....", order.getId(), order.getOrderStatus());
    }

}
