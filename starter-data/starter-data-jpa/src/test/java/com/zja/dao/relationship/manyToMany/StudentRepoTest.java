package com.zja.dao.relationship.manyToMany;

import com.zja.entitys.relationship.manyToMany.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 学生-单元测试类
 *
 * @author: zhengja
 * @since: 2024/04/19 16:45
 */
@SpringBootTest
public class StudentRepoTest {

    @Autowired
    StudentRepo studentRepo;

    @Test
    public void test() {
        studentRepo.findAll();
    }

    @Test
    public void test_1() {
        Student student1 = new Student();
        student1.setName("John1");

        Student student2 = new Student();
        student2.setName("John2");

        // 保存Student实体，会触发生成关联表数据
        studentRepo.save(student1);
        studentRepo.save(student2);
    }

    @Test
    public void test_2() {

    }

    @Test
    public void test_3() {
        studentRepo.deleteById("6626034377f6dc5e3978b01d");
        // studentRepo.deleteAll();
    }

}