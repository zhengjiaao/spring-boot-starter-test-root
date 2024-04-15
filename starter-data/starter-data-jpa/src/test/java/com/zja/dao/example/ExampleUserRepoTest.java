package com.zja.dao.example;

import com.zja.JpaApplicationTest;
import com.zja.entitys.User;
import com.zja.entitys.example.ExampleUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * -单元测试类
 *
 * @author: zhengja
 * @since: 2024/04/15 13:16
 */
@SpringBootTest
public class ExampleUserRepoTest extends JpaApplicationTest {

    @Autowired
    ExampleUserRepo repo;

    @Test
    public void test_1() {
        for (int i = 0; i < 10; i++) {
            ExampleUser user = new ExampleUser();
            user.setName("李四");
            user.setLoginName("lisi");
            user.setAge(10 + i);
            user.setCreateTime(LocalDateTime.now());
            ExampleUser save = repo.save(user);
            System.out.println(save);
        }
    }

    @Test
    public void test_2() {
        long byName = repo.countByName("李四");
        System.out.println(byName);
    }

    @Test
    public void test_3() {
        Page<ExampleUser> users = page("李四", 1, 5);

        assertThat(users).isNotNull();
        for (ExampleUser user : users) {
            System.out.println(user.getName());
        }
    }

    public Page<ExampleUser> page(String name,
                                  Integer pageNum,
                                  Integer pageSize) {
        // 当前页， 每页记录数
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return repo.findByName(name, pageable);
    }

    @Test
    public void test_4() {
        List<ExampleUser> users = sort("李四");
        System.out.println(users);
    }

    /**
     * 列举了四种排序方式：
     * 1）直接创建Sort对象，适合对单一属性做排序
     * 2）通过Sort.Order对象创建Sort对象，适合对单一属性做排序
     * 3）通过属性的List集合创建Sort对象，适合对多个属性，采取同一种排序方式的排序
     * 4）通过Sort.Order对象的List集合创建Sort对象，适合所有情况，比较容易设置排序方式
     */
    public List<ExampleUser> sort(String name) {
        return repo.findByName(name, Sort.by("id", "createTime"));
    }

    @Test
    public void test_5() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<ExampleUser> userPage = repo.findByNameLike("%李%", pageable);
        for (ExampleUser exampleUser : userPage) {
            System.out.println(exampleUser.getName());
        }
    }
}