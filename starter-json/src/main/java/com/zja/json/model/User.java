package com.zja.json.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * User 数据传输
 *
 * @author: zhengja
 * @since: 2025/04/14 13:55
 */
@Data
// @JsonInclude(JsonInclude.Include.NON_NULL) // 忽略空字段，默认为ALWAYS，返回所有字段
@ApiModel("User")
public class User implements Serializable {
    @ApiModelProperty("id")
    private Long  id;

    @ApiModelProperty("名称")
    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Date date;

    private List<String> roles;
    private String secretField; // 需要忽略的字段
}