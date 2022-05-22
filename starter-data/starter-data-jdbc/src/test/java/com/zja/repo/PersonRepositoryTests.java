/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-05-22 14:47
 * @Since:
 */
package com.zja.repo;

import com.zja.DataJdbcApplicationTests;
import com.zja.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PersonRepositoryTests extends DataJdbcApplicationTests {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void findByFirstname() {
        List<Person> personList = personRepository.findByFirstname("22");
        System.out.println(personList);
    }

    @Test
    public void queryByFirstname() {
        List<Person> personList = personRepository.queryByFirstname("22");
        System.out.println(personList);
    }

    @Test
    public void queryByLastname() {
        List<Person> personList = personRepository.queryByLastname("222");
        System.out.println(personList);
    }

    @Test
    public void findById() {
        //未调用成功
        Optional<Person> p = personRepository.findById(1L);
        System.out.println(p);
    }
}
