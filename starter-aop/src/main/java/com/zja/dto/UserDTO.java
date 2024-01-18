package com.zja.dto;

import com.zja.aop.MyBodyParam;
import com.zja.aop.MyParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhengja@dist.com.cn
 * @data 2019/6/27 16:35
 */
@ApiModel(value = "用户信息")
@Data
public class UserDTO implements Serializable {
    @ApiModelProperty(value = "用户id")
    private String id;
    @ApiModelProperty(value = "用户名")
    @MyParam
    private String name;
    @ApiModelProperty(value = "时间")
    private Date date;
}
