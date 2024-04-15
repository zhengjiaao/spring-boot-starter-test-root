/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 9:54
 * @Since:
 */
package com.zja.entitys.audit;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 测试审计功能实体类
 *
 * @author: zhengja
 * @since: 2023/09/28 9:54
 */
@Getter
@Setter
@Entity
@Table(name = "jpa_audit_user")
public class AuditUserEntity extends BaseAuditingEntity {
    private String username;
    private String password;
}
