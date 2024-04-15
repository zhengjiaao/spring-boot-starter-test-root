package com.zja.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: zhengja
 * @since: 2024/04/15 11:06
 */
@Data
@ApiModel("用户附加信息 DTO")
public class UserAttachmentDTO {

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String mobilePhone;

    @ApiModelProperty("职位")
    private String position;
}
