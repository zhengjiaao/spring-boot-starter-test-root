package com.zja.dao.relationship.oneToMany;

import com.zja.JpaApplicationTest;
import com.zja.entitys.relationship.oneToMany.AChild;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * 单表操作，无外键关联情况
 *
 * @author: zhengja
 * @since: 2024/04/18 10:29
 */
public class AChildRepoTest extends JpaApplicationTest {

    @Autowired
    private AChildRepo childRepo;

    @Test
    public void test_save() {
        AChild aChild1 = new AChild();
        aChild1.setName("Child 1");
        childRepo.save(aChild1);

        AChild aChild2 = new AChild();
        aChild2.setName("Child 2");
        childRepo.save(aChild2);
    }

    @Test
    public void test_1() {
        List<AChild> children = childRepo.findAll();
        System.out.println(children);
    }

    @Test
    public void test_2() {
        Optional<AChild> byId = childRepo.findById(Long.valueOf("23"));
        if (byId.isPresent()) {
            System.out.println(byId.get());
        } else {
            System.out.println("不存在.");
        }
    }

    @Test
    public void test_3() {
        childRepo.deleteById(Long.valueOf("23"));
    }

    @Test
    public void test_4() {
        childRepo.deleteAll();
    }

}