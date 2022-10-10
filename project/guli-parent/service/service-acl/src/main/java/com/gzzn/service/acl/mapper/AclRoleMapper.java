package com.gzzn.service.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzzn.service.acl.entity.AclRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lyq
 * @date 2021/12/5 12:32
 */
@Mapper
public interface AclRoleMapper extends BaseMapper<AclRoleEntity> {
    @Select("<script>SELECT p.id from acl_role r\n" +
            "\tLEFT JOIN acl_role_permission rp on r.id = rp.role_id\n" +
            "\tLEFT JOIN acl_permission p on p.id = rp.permission_id\n" +
            "\tWHERE r.id = #{id} </script>")
    List<String> selectPermissionIdsByRoleId(String id);


    @Select("<script>SELECT rp.id from acl_role r\n" +
            "\tLEFT JOIN acl_role_permission rp on r.id = rp.role_id\n" +
            "\tLEFT JOIN acl_permission p on p.id = rp.permission_id\n" +
            "\tWHERE r.id = #{id} </script>")
    List<String> selectRolePermissionIdsByRoleId(String id);
}
