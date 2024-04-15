/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 17:50
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
 * @since: 2023/08/10 17:50
 */
@Data
@ApiModel("RoleDTO")
public class RoleDTO implements Serializable {

    @ApiModelProperty("角色 id")
    private String id;

    @ApiModelProperty("角色名")
    private String name;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty("当前角色状态，小于 0 表示停用")
    private Integer state;

    @ApiModelProperty("是否为内置角色（系统自动创建）")
    private Boolean internal;

    @ApiModelProperty("排列顺序")
    private Long sort;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdDate;

    @ApiModelProperty("修改时间")
    private LocalDateTime lastModifiedDate;
}