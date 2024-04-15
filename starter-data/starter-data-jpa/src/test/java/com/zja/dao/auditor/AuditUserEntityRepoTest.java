/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 10:14
 * @Since:
 */
package com.zja.dao.auditor;

import com.zja.JpaApplicationTest;
import com.zja.dao.audit.AuditUserEntityRepo;
import com.zja.entitys.audit.AuditUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * 审计功能测试
 *
 * @author: zhengja
 * @since: 2023/09/28 10:14
 */
public class AuditUserEntityRepoTest extends JpaApplicationTest {

    @Autowired
    AuditUserEntityRepo repo;

    @Test
    public void save_test() {

        AuditUserEntity auditUser = new AuditUserEntity();
        auditUser.setUsername("李四");
        auditUser.setPassword("123");

        AuditUserEntity entity = repo.save(auditUser);

        System.out.println(entity);
        System.out.println(entity.getId());
        System.out.println(entity.getCreateTime());
        System.out.println(entity.getUpdateTime());
        System.out.println(entity.getCreateUserId());
        System.out.println(entity.getUpdateUserId());
    }

    @Test
    public void update_test() {

        String id = "6514e59a43b6d9914d76283e";

        Optional<AuditUserEntity> byId = repo.findById(id);
        AuditUserEntity auditUser = byId.orElseThrow(() -> new RuntimeException("id 错误."));
        System.out.println("===更新前");

        System.out.println(auditUser);
        System.out.println(auditUser.getId());
        System.out.println(auditUser.getCreateTime());
        System.out.println(auditUser.getUpdateTime());
        System.out.println(auditUser.getCreateUserId());
        System.out.println(auditUser.getUpdateUserId());

        System.out.println("===更新后");

        auditUser.setPassword("123456"); // 更新密码
        AuditUserEntity entity = repo.save(auditUser);

        System.out.println(entity);
        System.out.println(entity.getId());
        System.out.println(entity.getCreateTime());
        System.out.println(entity.getUpdateTime());
        System.out.println(entity.getCreateUserId());
        System.out.println(entity.getUpdateUserId());

    }
}
