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
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author: zhengja
 * @since: 2023/09/27 17:27
 */
public class AuditorAwareImplB implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //当前用户id
        String currentUserId = IdGeneratorUtil.objectId();

        //一般从项目请求token中获取当前登录用户
        return StringUtils.hasText(currentUserId) ? Optional.of(currentUserId) : Optional.of("system");
    }

}