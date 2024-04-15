package com.zja.dao.example;

import com.zja.entitys.example.ExampleUser;
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
 * @author: zhengja
 * @since: 2024/04/15 13:16
 */
@Repository
public interface ExampleUserRepo extends
        JpaRepository<ExampleUser, String>,
        CrudRepository<ExampleUser, String>,
        JpaSpecificationExecutor<ExampleUser> {

    Optional<ExampleUser> findByLoginName(String loginName);

    List<ExampleUser> findByIdIn(List<String> ids);

    //统计查询
    long countByName(String name);

    //删除查询
    long deleteByName(String name);

    List<ExampleUser> removeByName(String name);

    //实体类名称
    @Query("select u from #{#entityName} u where u.name = ?1")
//    @Query("select u from ExampleUser u where u.name = ?1")
    List<ExampleUser> findByName(String name);

    //分页查询
    Page<ExampleUser> findByName(String lastname, Pageable pageable);

    //分页模糊查询
    Page<ExampleUser> findByNameLike(String lastname, Pageable pageable);

    List<ExampleUser> findByName(String name, Sort sort);
    //List<ExampleUser> findByName(String lastname, Pageable pageable);

/*    //使用 SpEL 表达式 - 通配符快捷方式
    @Query("select u from ExampleUser u where u.name like %:#{[0]}% and u.name like %:lastname%")
    List<ExampleUser> findByNameWithSpelExpression(@Param("name") String name);

    //修改查询
    @Modifying
    @Query("update ExampleUser u set u.firstname = ?1 where u.name = ?2")
    int setFixedFirstnameFor(String firstname, String name);*/
}
