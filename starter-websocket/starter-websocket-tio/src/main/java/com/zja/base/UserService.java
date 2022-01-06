/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-01 13:07
 * @Since:
 */
package com.zja.base;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户操作管理(模拟数据)
 */
@Service
public class UserService {

    private static List<UserEntity> userEntityList;

    static {
        userEntityList = Arrays.asList(
                UserEntity.builder().id(1L).name("李四").age(21).build(),
                UserEntity.builder().id(2L).name("张三").age(20).build(),
                UserEntity.builder().id(3L).name("小倩").age(18).build(),
                UserEntity.builder().id(4L).name("冰冰").age(20).build()
        );
    }

    public UserEntity findOne(long id) {
        for (UserEntity userEntity : userEntityList) {
            if (userEntity.getId().equals(id)) {
                return userEntity;
            }
        }
        return null;
    }

    public List<UserEntity> findAll() {
        return userEntityList;
    }


}
