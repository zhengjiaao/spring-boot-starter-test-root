/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-05-22 14:27
 * @Since:
 */
package com.zja.repo;

import com.zja.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

/**
 *
 */
@Repository
public interface PersonRepository extends /*CrudRepository<Person, Long>,*/ PagingAndSortingRepository<Person, Long> {

    List<Person> findByFirstname(String firstname);

    @Query("select id,firstname, lastname from Person p where p.firstname = :firstname")
    List<Person> queryByFirstname(@Param("firstname") String firstname);

    Person findByFirstnameAndLastname(String firstname, String lastname);

    Page<Person> findByLastname(String lastname, Pageable pageable);

    /**
     * 注解 @Query 中的查询参数，必须配合 @Param("") 注解
     */
    @Query("SELECT * FROM person WHERE lastname = :lastname")
    List<Person> queryByLastname(@Param("lastname") String lastname);

    @Query("SELECT * FROM person WHERE lastname = :lastname")
    Stream<Person> streamByLastname(@Param("lastname") String lastname);

    /**
     * 可以指定以下返回类型：
     * void
     * int（更新记录数）
     * boolean（记录是否更新）
     */
    @Modifying
    @Query("UPDATE Person SET firstname = :firstname WHERE id = :id")
    boolean updateFirstname(@Param("id") Long id, @Param("firstname") String firstname);
}
