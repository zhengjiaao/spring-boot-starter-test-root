package com.zja.dao.example;

import com.zja.entitys.example.ExampleUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    //==================查询==================

    // 简单查询
    Optional<ExampleUser> findByLoginName(String loginName);
    // ExampleUser findByLoginName(String loginName);

    // 按loginName查询name值
    @Query("SELECT u.name FROM ExampleUser u WHERE u.loginName = ?1")
    Optional<String> findNameByLoginName(String loginName);
    // String findNameByLoginName(String loginName);

    // 批量查询
    List<ExampleUser> findByLoginNameIn(List<String> loginNames);

    // 模糊查询，支持：%name%、%name、name%
    List<ExampleUser> findByNameLike(String name);

    // 统计查询
    long countByName(String name);

    //==================查询-end==================


    //==================分页查询+排序==================

    // 分页查询
    Page<ExampleUser> findByName(String name, Pageable pageable);
    // List<ExampleUser> findByName(String name, Pageable pageable);

    // 分页模糊查询，支持：%name%、%name、name%
    Page<ExampleUser> findByNameLike(String name, Pageable pageable);

    // 按name模糊分页查询，并按age和createTime排序
    Page<ExampleUser> findByNameContainingIgnoreCaseOrderByAgeAscCreateTimeDesc(String name, Pageable pageable);

    // 排序查询
    List<ExampleUser> findByName(String name, Sort sort);

    //==================分页查询-end==================


    //==================SpEL 表达式 通配符 ==================

    // 按关键词name模糊分页查询，并按age和createTime排序，采用SpEL 表达式 通配符
    @Query("SELECT u FROM ExampleUser u WHERE u.name LIKE %:keyword% ORDER BY u.age ASC, u.createTime DESC")
    Page<ExampleUser> findByKeywordOrderByAgeAscCreateTimeDesc(@Param("keyword") String keyword, Pageable pageable);

    // 按实体类名称和name字段模糊分页查询，并按age和createTime排序，采用SpEL 表达式 通配符
    // 注：entityName=ExampleUser
    @Query("SELECT u FROM #{#entityName} u WHERE u.name LIKE %:keyword% ORDER BY u.age ASC, u.createTime DESC")
    Page<ExampleUser> findByEntityAndNameLikeOrderByAgeAscCreateTimeDesc(@Param("entityName") String entityName, @Param("keyword") String keyword, Pageable pageable);

    // 实体类名称
    @Query("select u from #{#entityName} u where u.name = :name")
    // @Query("select u from ExampleUser u where u.name = ?1")
    List<ExampleUser> querySpELByName(String entityName, String name);

    //==================SpEL 表达式 通配符-end ==================


    //==================更新==================

    // 按loginName更新name值
    @Transactional
    @Modifying
    @Query("UPDATE ExampleUser u SET u.name = :newName WHERE u.loginName = :loginName")
    void updateNameByLoginName(String newName, String loginName);
    // @Query("update ExampleUser u set u.name = ?1 where u.loginName = ?2")
    // int updateNameByLoginName(String name, String loginName);

    // 按loginName更新name值，采用nativeQuery=true写法
    // @Modifying
    // @Query(value = "UPDATE example_user SET name = :newName WHERE login_name = :loginName", nativeQuery = true)
    // void updateNameByLoginName(String newName, String loginName);

    //==================更新-end==================

    //==================删除==================

    // 按 loginName 删除相关记录
    void deleteByLoginName(String loginName);

    // 按loginName删除name值
    @Transactional
    @Modifying
    @Query("UPDATE ExampleUser u SET u.name = NULL WHERE u.loginName = :loginName")
    void deleteNameByLoginName(String loginName);

    // 按loginName删除name值，采用nativeQuery=true写法
    // @Modifying
    // @Query(value = "UPDATE example_user SET name = NULL WHERE login_name = :loginName", nativeQuery = true)
    // void deleteNameByLoginName(String loginName);

    // 按name删除，并返回删除的相关记录个数
    long deleteByName(String name);

    // 按name删除，并放回删除的相关记录
    List<ExampleUser> removeByName(String name);

    //==================删除-end==================

}
