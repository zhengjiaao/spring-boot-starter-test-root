package com.zja.dao.relationship.manyToMany;

import com.zja.entitys.relationship.manyToMany.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 课程-单元测试类
 *
 * @author: zhengja
 * @since: 2024/04/19 16:45
 */
@SpringBootTest
public class CourseRepoTest {

    @Autowired
    CourseRepo courseRepo;

    @Test
    public void test() {
        courseRepo.findAll();
    }

    @Test
    public void test_1() {
        Course course1 = new Course();
        course1.setName("Math1");

        Course course2 = new Course();
        course2.setName("Math2");

        // 保存Course实体
        courseRepo.save(course1);
        courseRepo.save(course2);
    }

    @Test
    public void test_2() {

    }

    @Test
    public void test_3() {
        // courseRepo.deleteById("");
        // courseRepo.deleteAll();
    }

}