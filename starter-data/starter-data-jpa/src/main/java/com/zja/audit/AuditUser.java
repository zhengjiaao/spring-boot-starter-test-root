/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 12:32
 * @Since:
 */
package com.zja.audit;

import lombok.Data;

/**
 * @author: zhengja
 * @since: 2023/09/28 12:32
 */
@Data
public class AuditUser {
    private String userId;
    private String userName;
}
