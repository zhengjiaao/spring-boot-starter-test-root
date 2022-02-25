/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-14 11:03
 * @Since:
 */
package com.zja.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "users")
@ApiModel("用户信息实体类")
public class UserDTO implements Serializable {

    @ApiModelProperty("唯一标识 ID")
    private String id;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("创建时间")
    private Date createTime;
}
