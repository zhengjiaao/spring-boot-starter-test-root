/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-27 17:27
 * @Since:
 */
package com.zja.audit;

import com.zja.util.IdGeneratorUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * @author: zhengja
 * @since: 2023/09/27 17:27
 */
public class AuditorAwareImplA implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        //模拟当前登录用户
        AuditUser currentUser = new AuditUser();
        currentUser.setUserId(IdGeneratorUtil.objectId());  //当前用户id
        currentUser.setUserName("李四");

        if (ObjectUtils.isEmpty(currentUser)) {
            return Optional.empty();
        }

        //一般项目中从spring security或token中获取
        return Optional.of(currentUser.getUserId());
    }

    //示例：从Spring Security中获取当前用户名
/*    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        String username = authentication.getName();
        return username;
    }*/
}