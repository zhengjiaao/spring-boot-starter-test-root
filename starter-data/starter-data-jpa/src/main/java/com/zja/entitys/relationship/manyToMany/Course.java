package com.zja.entitys.relationship.manyToMany;

import com.zja.entitys.base.BaseEntity;
import com.zja.util.IdGeneratorUtil;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程
 *
 * @author: zhengja
 * @since: 2024/04/19 16:37
 */
@Getter
@Setter
@Entity
@Table(name = "mm_course", uniqueConstraints = {
        @UniqueConstraint(name = "uk_course_sort", columnNames = {"sort"})
})
@EntityListeners(value = AuditingEntityListener.class)
public class Course extends BaseEntity {

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

    @ManyToMany(mappedBy = "courses") // // 在Course实体类中指定Student为中间表的维护方，维护方Student的操作会导致中间表的更新
    @OrderBy("sort")
    private List<Student> students = new ArrayList<>(); // 注，先初始化
    // private Set<Student> students = new LinkedHashSet<>(); // 注，先初始化

}