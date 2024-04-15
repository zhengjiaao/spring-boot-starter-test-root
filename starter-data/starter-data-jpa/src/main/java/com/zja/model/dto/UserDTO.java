/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 13:01
 * @Since:
 */
package com.zja.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: zhengja
 * @since: 2023/08/10 13:01
 */
@Data
@ApiModel("UserDTO")
public class UserDTO implements Serializable {

    @ApiModelProperty("用户 id")
    private String id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("当前用户状态，小于 0 表示停用")
    private Integer state;

    @ApiModelProperty("是否为内置用户（系统自动创建）")
    private Boolean internal;

    @ApiModelProperty("排列顺序")
    private Long sort;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdDate;

    @ApiModelProperty("修改时间")
    private LocalDateTime lastModifiedDate;

    @ApiModelProperty("用户附加信息")
    private UserAttachmentDTO userAttachment;

}