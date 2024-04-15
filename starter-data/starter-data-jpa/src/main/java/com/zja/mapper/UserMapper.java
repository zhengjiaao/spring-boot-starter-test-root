/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 13:03
 * @Since:
 */
package com.zja.mapper;

import com.zja.entitys.User;
import com.zja.entitys.UserAttachment;
import com.zja.model.dto.UserDTO;
import com.zja.model.request.UserAttachmentRequest;
import com.zja.model.request.UserRequest;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author: zhengja
 * @since: 2023/08/10 13:03
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserRequest request);

    UserDTO map(User entity);

    User map(UserDTO dto);

    List<UserDTO> mapList(List<User> entityList);

    UserAttachment mapAttachment(UserAttachmentRequest userAttachmentDTO);


}
