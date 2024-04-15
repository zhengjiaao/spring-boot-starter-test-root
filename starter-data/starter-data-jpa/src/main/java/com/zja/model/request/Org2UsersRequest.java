package com.zja.model.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: zhengja
 * @since: 2023/08/10 13:15
 */
@Data
@ApiModel("组织机构关联用户 request")
public class Org2UsersRequest {

    @NotEmpty
    private String orgId;

    @NotNull
    private List<String> userIds;
}
