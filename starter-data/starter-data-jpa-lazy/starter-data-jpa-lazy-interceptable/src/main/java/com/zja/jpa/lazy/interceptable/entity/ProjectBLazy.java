package com.zja.jpa.lazy.interceptable.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
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
@Table(name = "t_project_b_lazy")
@EntityListeners(value = AuditingEntityListener.class)
public class ProjectBLazy implements Serializable, PersistentAttributeInterceptable {

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
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "config_json")
    private JSONObject configJson;

    /**
     * 文本 or json字符串存储字段
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "config_text")
    private String configText;

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


    // 以下是实现懒加载

    @Transient
    private PersistentAttributeInterceptor interceptor;

    @Override
    public PersistentAttributeInterceptor $$_hibernate_getInterceptor() {
        return interceptor;
    }

    @Override
    public void $$_hibernate_setInterceptor(PersistentAttributeInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    // 必须的 getter 方法，否则不会懒加载
    public JSONObject getConfigJson() {
        // 避免二次读取数据库
        if (this.configJson != null) {
            return this.configJson;
        }

        // 自定义懒加载实现，需配合 @Basic(fetch = FetchType.LAZY) + enable_lazy_load_no_trans: true
        return (JSONObject) interceptor.readObject(this, "configJson", this.configJson);
    }

    // 必须的 getter 方法，否则不会懒加载
    public String getConfigText() {
        // 避免二次读取数据库
        if (this.configText != null) {
            return this.configText;
        }

        // 自定义懒加载实现，需配合 @Basic(fetch = FetchType.LAZY) + enable_lazy_load_no_trans: true
        return (String) interceptor.readObject(this, "configText", this.configText);
    }

    // 必须的 setter 方法，否则不会更新
    public void setConfigJson(JSONObject configJson) {
        if (configJson != null) {
            if (interceptor != null) {
                // 若更新不生效，未执行更新SQL语句时，需要 @Transactional 注解，若单元测试时，需配合 @Rollback(false)
                interceptor.writeObject(this, "configJson", this.configJson, configJson);
            }
        }

        this.configJson = configJson;
    }

    // 必须的 setter 方法，否则不会更新
    public void setConfigText(String configText) {
        if (configText != null) {
            if (interceptor != null) {
                // 若更新不生效，未执行更新SQL语句时，需要 @Transactional 注解，若单元测试时，需配合 @Rollback(false)
                interceptor.writeObject(this, "configText", this.configText, configText);
            } else { // 保存时，interceptor拦截器会是空
                // 可以选择抛出异常、记录日志或采取其他措施
                // throw new IllegalStateException("Interceptor is not initialized");
            }
        }
        this.configText = configText;
    }
}