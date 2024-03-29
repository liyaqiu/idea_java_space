package com.gzzn.service.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzzn.service.acl.entity.AclPermissionEntity;
import com.gzzn.service.acl.entity.AclUserEntity;
import com.gzzn.service.acl.vo.resp.QueryAuthoritiesByUsernameVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/5 12:32
 */
@Mapper
public interface AclPermissionMapper extends BaseMapper<AclPermissionEntity> {

    @Select("<script>\n" +
            "SELECT u.id,u.username,u.password,u.locked,p.permit FROM acl_user u  \n" +
            "\tLEFT JOIN acl_user_role ur ON u.id = ur.user_id \n" +
            "\tLEFT JOIN acl_role r ON r.id = ur.role_id\n" +
            "\tLEFT JOIN acl_role_permission rp ON rp.role_id = r.id\n" +
            "\tLEFT JOIN acl_permission p ON p.id = rp.permission_id\n" +
            "\twhere u.username = #{username}\n" +
            "</script>")
    List<Map<String,Object>> selectAuthoritiesByUsername(String username);


    @Select("<script>SELECT DISTINCT p.*\n" +
            "\tFROM acl_user u \n" +
            "\tLEFT JOIN acl_user_role ur ON u.id = ur.user_id \n" +
            "\tLEFT JOIN acl_role r ON r.id = ur.role_id \n" +
            "\tLEFT JOIN acl_role_permission rp ON rp.role_id = r.id \n" +
            "\tLEFT JOIN acl_permission p ON p.id = rp.permission_id \n" +
            "where u.username = #{username} and p.type = 0 ORDER BY p.sort asc" +
            "</script>")
    List<AclPermissionEntity> selectMenuByUsername(String username);

    // TODO: 2022/10/8 优化可以与  selectAuthoritiesByUsername 写成一条
    @Select("<script>" +
            "SELECT permit FROM acl_user u \n" +
            "\tLEFT JOIN acl_user_role ur ON u.id = ur.user_id \n" +
            "\tLEFT JOIN acl_role r ON r.id = ur.role_id \n" +
            "\tLEFT JOIN acl_role_permission rp ON rp.role_id = r.id \n" +
            "\tLEFT JOIN acl_permission p ON p.id = rp.permission_id \n" +
            "where u.username = #{username} and permit is not null" +
            "</script>")
    List<String> selectFAuthoritiesByUsername(String username);


    @Select("<script>SELECT  DISTINCT u.username from acl_user u\n" +
            "\tLEFT JOIN acl_user_role ur on ur.user_id = u.id\n" +
            "\tLEFT JOIN acl_role_permission rp on rp.role_id = ur.role_id\n" +
            "\tLEFT JOIN acl_permission p on p.id = rp.permission_id\n" +
            "\tWHERE p.id = #{permissionId}</script>")
    List<String> selectUsernamesByPermissionId(String permissionId);
}
