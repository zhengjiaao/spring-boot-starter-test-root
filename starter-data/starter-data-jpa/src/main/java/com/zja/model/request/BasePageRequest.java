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

import java.io.Serializable;

/**
 * @author: zhengja
 * @since: 2023/08/10 13:15
 */
@Setter
@ApiModel("BasePageRequest")
public class BasePageRequest implements Serializable {
    @ApiModelProperty("页码 从第1页开始")
    private Integer page = 1;
    @Getter
    @ApiModelProperty("每页数量 默认 10")
    private Integer size = 10;

    public Integer getPage() {
        //jpa page 从 0 开始
        return page - 1;
    }
}