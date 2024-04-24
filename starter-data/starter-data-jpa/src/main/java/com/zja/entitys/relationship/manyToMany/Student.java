package com.zja.entitys.relationship.manyToMany;

import com.zja.entitys.base.BaseEntity;
import com.zja.util.IdGeneratorUtil;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 学生
 *
 * @author: zhengja
 * @since: 2024/04/19 16:23
 */
@Getter
@Setter
@Entity
@Table(name = "mm_student", uniqueConstraints = {
        @UniqueConstraint(name = "uk_student_sort", columnNames = {"sort"})
})
@EntityListeners(value = AuditingEntityListener.class)
public class Student extends BaseEntity {

    /**
     * 名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 排列顺序
     */
    @Column(nullable = false, unique = true)
    private Long sort = IdGeneratorUtil.nextSortLong();

    // 关系维护方 @JoinTable，Student操作会导致中间表的更新
    @ManyToMany
    // @ManyToMany(cascade = CascadeType.REMOVE)
    @OrderBy("sort")
    @JoinTable(
            name = "mm_student_course", // 中间表
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>(); // 注，先初始化，有序的，推荐自定义排序字段
    // private Set<Course> courses = new LinkedHashSet<>(); // 注，先初始化，无序的，不支持排序，需自定排序字段
}