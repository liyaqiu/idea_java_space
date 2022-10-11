package com.gzzn.service.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzzn.service.acl.entity.AclRoleEntity;
import com.gzzn.service.acl.entity.AclUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lyq
 * @date 2021/12/5 12:32
 */
@Mapper
public interface AclUserRoleMapper extends BaseMapper<AclUserRoleEntity> {

    @Select("<script>SELECT r.name from acl_user_role ur \n" +
            "\tLEFT JOIN acl_role r ON r.id  = ur.role_id\n" +
            "\tWHERE ur.user_id = #{id} </script>")
    List<String> selectRoleNamesByUserId(String id);

}
