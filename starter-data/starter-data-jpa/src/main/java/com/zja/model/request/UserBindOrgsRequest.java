/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 17:05
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
 * 给用户绑定组织机构
 *
 * @author: zhengja
 * @since: 2023/08/10 17:05
 */
@Data
@AllArgsConstructor
@ApiModel("UserBindOrgsRequest")
public class UserBindOrgsRequest implements Serializable {

    @NotBlank
    private String userId;

    @NotNull
    private List<String> orgIds;

}