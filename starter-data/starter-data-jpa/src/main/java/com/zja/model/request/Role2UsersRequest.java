package com.zja.model.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: zhengja
 * @since: 2023/08/10 13:15
 */
@Data
@ApiModel("为角色绑定用户 request")
@AllArgsConstructor
public class Role2UsersRequest {

    @NotBlank
    private String roleId;

    @NotNull
    private List<String> usersId;
}
