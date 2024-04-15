/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 16:37
 * @Since:
 */
package com.zja.model.dto;

import com.zja.define.OrgTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: zhengja
 * @since: 2023/08/10 16:37
 */
@Data
@ApiModel("OrgDTO")
public class OrgDTO implements Serializable {
    @ApiModelProperty("组织机构id")
    private String id;

    @ApiModelProperty("组织机构名")
    private String name;

    @ApiModelProperty("当前组织机构状态")
    private Integer state;

    @ApiModelProperty("是否为内置组织机构（系统自动创建）")
    private Boolean internal;

    @ApiModelProperty("排列顺序")
    private Long sort;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdDate;

    @ApiModelProperty("修改时间")
    private LocalDateTime lastModifiedDate;

    @ApiModelProperty("父组织机构，此值为 -1 表示顶层组织机构")
    private String parentOrgId;

    @ApiModelProperty("组织机构类型，REGION -> 行政区划, ORGANIZATION -> 行政单位, DEPARTMENT -> 内部机构")
    private OrgTypeEnum orgType;

    @ApiModelProperty("当前组织机构所属的行政区划的代码")
    private String regionCode;

    @ApiModelProperty("层级名称")
    private String levelNames;

    @ApiModelProperty("层级 id")
    private String levelIds;

    @ApiModelProperty("是否有子集")
    private Boolean hasChildren;
}