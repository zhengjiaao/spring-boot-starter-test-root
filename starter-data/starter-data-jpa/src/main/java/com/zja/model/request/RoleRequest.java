/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 17:51
 * @Since:
 */
package com.zja.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhengja
 * @since: 2023/08/10 17:51
 */
@Data
@ApiModel("RoleRequest 新增 或 更新 角色信息")
public class RoleRequest implements Serializable {

    @ApiModelProperty("角色名")
    private String name;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty("当前角色状态，小于 0 表示停用")
    private Integer state;

}