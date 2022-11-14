/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-11-14 10:52
 * @Since:
 */
package com.zja.repositorys;

import com.zja.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class PersonRepositoryTests {

    @Resource
    PersonRepository personRepository;

    @Test
    void save() {
        Person person = new Person();
        person.setId(1L);
        person.setName("lisi");
        personRepository.save(person);
    }

    @Test
    void findAll() {
        List<Person> all = (List<Person>) personRepository.findAll();
        System.out.println(all);
    }

    @Test
    void findByName() {
        List<Person> all = (List<Person>) personRepository.findByName("lisi");
        System.out.println(all);
    }
}
