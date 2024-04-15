/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-10 16:37
 * @Since:
 */
package com.zja.mapper;

import com.zja.entitys.Org;
import com.zja.model.dto.OrgDTO;
import com.zja.model.request.OrgRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

/**
 * @author: zhengja
 * @since: 2023/08/10 16:37
 */
@Mapper(componentModel = "spring")
public interface OrgMapper {

    Org map(OrgRequest request);

    @Mapping(source = "parent.id", target = "parentOrgId")
    OrgDTO map(Org entity);

    Org map(OrgDTO dto);

    List<OrgDTO> mapList(List<Org> entityList);

    @Mapping(source = "parent.id", target = "parentOrgId")
    List<OrgDTO> mapList(Collection<Org> orgs);
}
