/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-26 16:35
 * @Since:
 */
package com.zja.redis.repositories;

import com.zja.redis.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface UserDao extends CrudRepository<UserEntity,Long> {

}
