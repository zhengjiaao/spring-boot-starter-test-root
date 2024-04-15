/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 13:04
 * @Since:
 */
package com.zja.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhengja
 * @since: 2023/08/10 13:04
 */
@Data
@ApiModel("UserRequest 新增 或 更新 用户信息")
public class UserRequest implements Serializable {

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("当前用户状态，小于 0 表示停用")
    private Integer state;

    @ApiModelProperty("用户附加信息")
    private UserAttachmentRequest userAttachment;

    @ApiModelProperty("用户绑定组织结构id列表")
    private List<String> orgIds;

}