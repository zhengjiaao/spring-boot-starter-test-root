/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-14 11:04
 * @Since:
 */
package com.zja.repositorys;

import com.zja.entitys.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用法参考：https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#preface
 *
 * Repository
 * CrudRepository 常用的
 * PagingAndSortingRepository 分页、排序
 * JpaRepository 常用的
 * QuerydslPredicateExecutor 扩展，可选的
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>/*, PagingAndSortingRepository<User, Long>, QuerydslPredicateExecutor<User>*/ {

    //统计查询
    long countByUserName(String username);

    //删除查询
    long deleteByUserName(String username);
    List<UserEntity> removeByUserName(String username);

    //实体类名称
    @Query("select u from #{#entityName} u where u.userName = ?1")
//    @Query("select u from UserEntity u where u.userName = ?1")
    List<UserEntity> findByUserName(String username);

    //分页查询
    Page<UserEntity> findByUserName(String lastname, Pageable pageable);
    List<UserEntity> findByUserName(String username, Sort sort);
    //List<User> findByUserName(String lastname, Pageable pageable);

/*    //使用 SpEL 表达式 - 通配符快捷方式
    @Query("select u from UserEntity u where u.username like %:#{[0]}% and u.username like %:lastname%")
    List<UserEntity> findByUserNameWithSpelExpression(@Param("username") String username);

    //修改查询
    @Modifying
    @Query("update UserEntity u set u.firstname = ?1 where u.username = ?2")
    int setFixedFirstnameFor(String firstname, String username);*/
}
