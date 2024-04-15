/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 13:15
 * @Since:
 */
package com.zja.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: zhengja
 * @since: 2023/08/10 13:15
 */
@Getter
@Setter
@ApiModel("User 分页搜索参数")
public class UserPageSearchRequest extends BasePageRequest {

    @ApiModelProperty("关键词（用户名、登录名）")
    private String key;

    @ApiModelProperty("角色id")
    private List<String> roleIds;

    @ApiModelProperty("组织机构id")
    private List<String> orgIds;

    @ApiModelProperty("资源状态（启用：1，禁用：-1）")
    private Integer state;
}