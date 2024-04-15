/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-14 11:04
 * @Since:
 */
package com.zja.dao;

import com.zja.entitys.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 用法参考：https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#preface
 * <p>
 * Repository
 * CrudRepository 常用的
 * PagingAndSortingRepository 分页、排序
 * JpaRepository 常用的
 * QuerydslPredicateExecutor 扩展，可选的
 */
@Repository
public interface UserRepo extends
        JpaRepository<User, String>,
        CrudRepository<User, String>,
        JpaSpecificationExecutor<User> {

    Optional<User> findByLoginName(String loginName);

    List<User> findByIdIn(List<String> ids);

    //统计查询
    long countByName(String name);

    //删除查询
    long deleteByName(String name);

    List<User> removeByName(String name);

    //实体类名称
    @Query("select u from #{#entityName} u where u.name = ?1")
//    @Query("select u from UserEntity u where u.name = ?1")
    List<User> findByName(String name);

    //分页查询
    Page<User> findByName(String lastname, Pageable pageable);

    List<User> findByName(String name, Sort sort);
    //List<User> findByName(String lastname, Pageable pageable);

/*    //使用 SpEL 表达式 - 通配符快捷方式
    @Query("select u from UserEntity u where u.name like %:#{[0]}% and u.name like %:lastname%")
    List<UserEntity> findByNameWithSpelExpression(@Param("name") String name);

    //修改查询
    @Modifying
    @Query("update UserEntity u set u.firstname = ?1 where u.name = ?2")
    int setFixedFirstnameFor(String firstname, String name);*/
}
