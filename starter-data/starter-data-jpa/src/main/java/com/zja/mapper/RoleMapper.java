/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 17:50
 * @Since:
 */
package com.zja.mapper;

import com.zja.entitys.Role;
import com.zja.model.dto.RoleDTO;
import com.zja.model.request.RoleRequest;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author: zhengja
 * @since: 2023/08/10 17:50
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role map(RoleRequest request);

    RoleDTO map(Role entity);

    Role map(RoleDTO dto);

    List<RoleDTO> mapList(List<Role> entityList);

    List<RoleDTO> mapList(Collection<Role> roles);
}
