package com.zja.dao.relationship.oneToMany;

import com.zja.JpaApplicationTest;
import com.zja.entitys.relationship.oneToMany.Customer;
import com.zja.entitys.relationship.oneToMany.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: zhengja
 * @since: 2024/04/24 10:31
 */
public class CustomerAndOrderRepoTest extends JpaApplicationTest {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepo orderRepo;


    @Test
    public void test_1() {
        customerRepo.deleteAll();
        orderRepo.deleteAll();
    }

    @Test
    public void test_2() {
        Order order1 = new Order();
        order1.setOrderName("11");
        Order order2 = new Order();
        order2.setOrderName("22");

        orderRepo.save(order1);
        orderRepo.save(order2);

        Customer customer = new Customer();
        customer.setName("customer-1");
        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        customerRepo.save(customer);
    }

    @Test
    public void test_3() {

    }

    @Test
    public void test_4() {

    }

    @Test
    public void test_5() {

    }

}
