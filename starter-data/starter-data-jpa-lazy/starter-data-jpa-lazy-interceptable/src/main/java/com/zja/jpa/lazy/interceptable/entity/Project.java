package com.zja.jpa.lazy.interceptable.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.*;
import org.hibernate.engine.spi.PersistentAttributeInterceptable;
import org.hibernate.engine.spi.PersistentAttributeInterceptor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Project 实体类
 *
 * @author: zhengja
 * @since: 2024/07/01 15:23
 */
@Getter
@Setter
@Entity
@Table(name = "t_project")
@EntityListeners(value = AuditingEntityListener.class)
public class Project implements Serializable {

    @Id
    private String id = String.valueOf(System.currentTimeMillis());

    /**
     * 名称
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * 项目周期
     */
    private String type;

    /**
     * 项目周期
     */
    private int cycle;

    /**
     * json 字符串存储字段
     */
    @Lob
    @Column(name = "config_json")
    private JSONObject configJson;  // jpa 默认把JSONObject字段类型生成为错误的oid，正确应是text(建议手动更改)
    // private String configJson;  // 注意：JSONObject 与 String 不能互换

    /**
     * 文本 or json字符串存储字段
     */
    @Lob
    @Column(name = "config_text")
    private String configText; // jpa 默认把String字段类型生成为text

    /**
     * 当前状态 正常 1，已删除 -1
     */
    @Column(name = "state")
    private Integer state = 1;

    /**
     * 是否为内置（true 系统自动创建）
     */
    @Column(name = "internal")
    private Boolean internal = false;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 排列顺序
     */
    @Column(name = "sort", nullable = false, unique = true)
    private Long sort = System.currentTimeMillis();

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    @CreatedDate
    private LocalDateTime createTime;

    /**
     * 最后一次修改时间
     */
    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}