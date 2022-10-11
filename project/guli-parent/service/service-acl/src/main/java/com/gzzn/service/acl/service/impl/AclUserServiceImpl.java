package com.gzzn.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.acl.converter.AclUserConverter;
import com.gzzn.service.acl.entity.AclPermissionEntity;
import com.gzzn.service.acl.entity.AclUserEntity;
import com.gzzn.service.acl.mapper.AclUserMapper;
import com.gzzn.service.acl.mapper.AclUserRoleMapper;
import com.gzzn.service.acl.service.AclPermissionService;
import com.gzzn.service.acl.service.AclUserService;
import com.gzzn.service.acl.vo.req.PageQueryAclUserVo;
import com.gzzn.service.acl.vo.resp.AclUserVo;
import com.gzzn.service.acl.vo.resp.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
@Slf4j
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUserEntity> implements AclUserService {

    @Autowired
    AclUserMapper aclUserMapper;
    @Autowired
    AclUserRoleMapper aclUserRoleMapper;

    @Autowired
    AclPermissionService aclPermissionService;

    @Override
    public UserInfoVo getUserInfoByUsername(String username) {
        UserInfoVo vo = new UserInfoVo();

        QueryWrapper wrapper  = new QueryWrapper();
        wrapper.eq("username", username);
        AclUserEntity aclUser = baseMapper.selectOne(wrapper);
        aclUser.setPassword(null);
        aclUser.setLocked(null);
        vo.setAclUser(aclUser);

        List<AclPermissionEntity> menuList = aclPermissionService.queryMenuByUsername(username);
        //返回用户菜单信息
        List<AclPermissionEntity> menuTreeList = buildTree(menuList);
        vo.setMenuTreeList(menuTreeList);

        List<String> authorities = aclPermissionService.queryFAuthoritiesByUsername(username);
        vo.setAuthorities(authorities);

        return vo;
    }

    @Override
    public Map<String, Object> pageQueryAclUser(long currentPage, long pageSize, PageQueryAclUserVo vo) {
        QueryWrapper wrapper = new QueryWrapper();
        if(vo.getBeginTime()!=null){
            wrapper.ge("gmt_create", vo.getBeginTime());
        }
        if(vo.getEndTime()!=null){
            wrapper.le("gmt_create", vo.getEndTime());
        }
        if(!StringUtils.isEmpty(vo.getUsername())){
            wrapper.like("username", vo.getUsername());
        }

        wrapper.orderByDesc("gmt_create");

        IPage<AclUserEntity> page = new Page<>(currentPage,pageSize);

        aclUserMapper.selectPage(page, wrapper);

        List<AclUserVo> aclUserVoList = AclUserConverter.INSTANCE.convert(page.getRecords());

        for (AclUserVo aclUserVo : aclUserVoList) {
            List<String> roleNames = aclUserRoleMapper.selectRoleNamesByUserId(aclUserVo.getId());
            aclUserVo.setRoleNames(roleNames);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("records", aclUserVoList);
        return map;
    }

    @Override
    @Transactional
    public void removeAclUser(String id) {
        if (aclUserMapper.deleteById(id)!=1) {
            throw new RuntimeException("删除用户失败");
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", id);
        aclUserRoleMapper.delete(wrapper);
    }

    @Override
    public void resetAclUserPassword(AclUserEntity aclUser) {
        if (aclUserMapper.updateById(aclUser)!=1) {
            throw new RuntimeException("更新失败");
        }
    }


    private List<AclPermissionEntity> buildTree(List<AclPermissionEntity> menuList){
        List<AclPermissionEntity> menuTreeList = new ArrayList<>();
        for (AclPermissionEntity p1 : menuList) {
            for (AclPermissionEntity p2 : menuList) {
                if(p1.getId().equals(p2.getParentId())){
                    p1.getChildren().add(p2);
                }
            }
            if(p1.getParentId() == null){
                menuTreeList.add(p1);
            }
        }
        return menuTreeList;
    }
}
