/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-26 16:36
 * @Since:
 */
package com.zja.redis.repositories;

import com.zja.redis.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 *
 */
@SpringBootTest
public class UserDaoTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void save(){
        userDao.save(UserEntity.builder().name("李四").age(22).build());

        List<UserEntity> userEntityList = (List<UserEntity>) userDao.findAll();
        for (UserEntity userEntity : userEntityList) {
            System.out.println(userEntity);
        }
    }

}
