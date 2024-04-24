package com.zja.dao.relationship.manyToMany;

import com.zja.entitys.relationship.manyToMany.Course;
import com.zja.entitys.relationship.manyToMany.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhengja
 * @since: 2024/04/19 16:42
 */
@Repository
public interface StudentRepo extends
        JpaRepository<Student, String>,
        CrudRepository<Student, String>,
        JpaSpecificationExecutor<Student> {

    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId")
    Page<Course> findStudentsByCourseId(@Param("courseId") String courseId, Pageable pageable);

    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.id IN :courseIds")
    Page<Course> findStudentsByCourseIds(@Param("courseIds") List<String> courseIds, Pageable pageable);
}
