/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 17:41
 * @Since:
 */
package com.zja.model.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author: zhengja
 * @since: 2023/08/10 17:41
 */
@Data
@AllArgsConstructor
@ApiModel("OrgBindRolesRequest")
public class OrgBindRolesRequest implements Serializable {

    @NotBlank
    private String orgId;

    @NotNull
    private List<String> roleIds;
}