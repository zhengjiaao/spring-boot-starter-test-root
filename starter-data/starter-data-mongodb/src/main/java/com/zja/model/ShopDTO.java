/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-25 16:08
 * @Since:
 */
package com.zja.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "文档数据")
public class ShopDTO {
    @ApiModelProperty(value = "文档id")
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String name;
}
