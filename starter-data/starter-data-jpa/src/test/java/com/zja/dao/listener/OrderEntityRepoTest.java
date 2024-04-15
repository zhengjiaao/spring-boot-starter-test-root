/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 11:06
 * @Since:
 */
package com.zja.dao.listener;

import com.zja.JpaApplicationTest;
import com.zja.entitys.listener.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * 实体监听器、自定义实体的监听器测试
 *
 * @author: zhengja
 * @since: 2023/09/28 11:06
 */
public class OrderEntityRepoTest extends JpaApplicationTest {

    @Autowired
    private OrderEntityRepo repo;

    @Test
    public void save_test() throws InterruptedException {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setPrice(BigDecimal.valueOf(55));

        System.out.println("===开始");

        //新增
        OrderEntity entity = repo.save(orderEntity);

        //查询
        Optional<OrderEntity> byId = repo.findById(entity.getId());
        OrderEntity order1 = byId.orElseThrow(() -> new RuntimeException("id 错误."));

        //更新
        order1.setOrderStatus(2);
        OrderEntity order = repo.save(order1);

        //删除
        repo.delete(order);

        System.out.println("===结束");
    }
}
