/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-27 17:39
 * @Since:
 */
package com.zja.entitys.audit;

import com.zja.entitys.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 审计功能抽象类
 *
 * @author: zhengja
 * @since: 2023/09/27 17:39
 */
@Getter
@Setter
@ToString(callSuper = true)
@MappedSuperclass // @MappedSuperclass 注释指定的类可以以与实体相同的方式映射，除了映射仅适用于它的子类之外，因为映射超类本身不存在表。
@EntityListeners(AuditingEntityListener.class) //添加审计监听器
public class BaseAuditingEntity extends BaseEntity {

    @CreatedDate
    private LocalDateTime createTime; // 创建时间
    @LastModifiedDate
    private LocalDateTime updateTime; // 最后一次修改时间

    @CreatedBy
    private String createUserId;    //创建人
    @LastModifiedBy
    private String updateUserId;    //最后一次修改人
}
