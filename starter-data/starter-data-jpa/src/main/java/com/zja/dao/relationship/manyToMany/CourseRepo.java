package com.zja.dao.relationship.manyToMany;

import com.zja.entitys.relationship.manyToMany.Course;
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
public interface CourseRepo extends
        JpaRepository<Course, String>,
        CrudRepository<Course, String>,
        JpaSpecificationExecutor<Course> {

    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId")
    Page<Course> findCoursesByStudentId(@Param("studentId") String studentId, Pageable pageable);

    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.id IN :studentIds")
    Page<Course> findCoursesByStudentIds(@Param("studentIds") List<String> studentIds, Pageable pageable);
}
