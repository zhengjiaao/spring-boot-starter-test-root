/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 17:21
 * @Since:
 */
package com.zja.model.request;

import com.zja.define.OrgTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhengja
 * @since: 2023/08/10 17:21
 */
@Data
@ApiModel("OrgUpdateRequest")
public class OrgUpdateRequest implements Serializable {

    @ApiModelProperty("组织机构名")
    private String name;

    @ApiModelProperty("当前组织机构状态")
    private Integer state;

    @ApiModelProperty("父组织机构 id")
    private String parentOrgId;

    @ApiModelProperty("组织机构类型，REGION -> 行政区划, ORGANIZATION -> 行政单位, DEPARTMENT -> 内部机构")
    private OrgTypeEnum orgType;

    @ApiModelProperty("当前组织机构所属的行政区划的代码")
    private String regionCode;

}