/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-14 11:04
 * @Since:
 */
package com.zja.dao;

import com.zja.entitys.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查看所有方法路径：http://localhost:8080/api/user/search 或 http://localhost:8080/${server.servlet.context-path}/api/user/search
 */
@RepositoryRestResource(path = "user") // 可选的 http://localhost:8080/api/user/
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    /**
     * http://localhost:8080/api/user/search/findByUserName
     */
    List<UserEntity> findByUserName(String userName);

    /**
     * http://localhost:8080/api/user/search/username
     */
//    @RestResource(path = "username", rel = "username")
//    List<UserEntity> findByUserName(String userName);

    /**
     * http://localhost:8080/api/age/search/findByAge
     */
    @RestResource(path = "age")
    List<UserEntity> findByAge(Integer age);

    @RestResource(path = "userNameStartsWith", rel = "userNameStartsWith")
    Page findByUserNameStartsWith(@Param("username") String userName, Pageable p);
}
