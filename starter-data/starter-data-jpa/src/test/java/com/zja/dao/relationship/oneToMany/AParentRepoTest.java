/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 16:30
 * @Since:
 */
package com.zja.dao.relationship.oneToMany;

import com.zja.JpaApplicationTest;
import com.zja.entitys.relationship.oneToMany.AParent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 单表操作，无外键关联情况
 *
 * @author: zhengja
 * @since: 2023/09/28 16:30
 */
public class AParentRepoTest extends JpaApplicationTest {

    @Autowired
    private AParentRepo parentRepo;

    @Test
    public void test_save() {
        AParent AParent = new AParent();
        AParent.setName("John");
        parentRepo.save(AParent);
    }

    @Test
    @Transactional // 添加此注解，解决Child懒加载导致no Session问题
    public void test_1() {
        List<AParent> all = parentRepo.findAll();
        System.out.println(all);
    }

    @Test
    @Transactional // 添加此注解，解决Child懒加载导致no Session问题
    public void test_2() {
        List<AParent> all = parentRepo.findAll();
        System.out.println(all);
        for (AParent parent : all) {
            System.out.println(parent.getName());
            System.out.println(parent.getChildren());
        }
    }

    @Test
    public void test_3() {
        parentRepo.deleteById(Long.valueOf("22"));
    }

    @Test
    public void test_4() {
        parentRepo.deleteAll();
    }

}
