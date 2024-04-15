package com.zja.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author: zhengja
 * @since: 2024/02/21 16:06
 */
@Getter
@Setter
@ApiModel("Role 分页搜索参数")
public class RolePageSearchRequest extends BasePageRequest {
    @ApiModelProperty("")
    private String name;

}