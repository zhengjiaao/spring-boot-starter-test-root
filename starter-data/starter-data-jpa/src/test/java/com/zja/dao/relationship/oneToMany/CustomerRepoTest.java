/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 14:58
 * @Since:
 */
package com.zja.dao.relationship.oneToMany;

import com.zja.JpaApplicationTest;
import com.zja.entitys.relationship.oneToMany.Customer;
import com.zja.entitys.relationship.oneToMany.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhengja
 * @since: 2023/09/28 14:58
 */
public class CustomerRepoTest extends JpaApplicationTest {

    @Autowired
    private CustomerRepo repo;

    @Autowired
    private OrderRepo orderRepo;

    @Test
    public void save_test() {

        List<Order> orderList = new ArrayList<>();

        Customer customer = new Customer();

//        Order order = new Order();
//        order.setOrderName();
//        orderList.add(order);
//
//
//        orderRepo.save();

        repo.save(customer);
    }

    @Test
    public void find_test() {

    }
}
