package com.zja.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author: zhengja
 * @since: 2024/02/21 16:06
 */
@Data
@ApiModel("RoleUpdateRequest")
public class RoleUpdateRequest implements Serializable {
    @ApiModelProperty("")
    private String name;

}