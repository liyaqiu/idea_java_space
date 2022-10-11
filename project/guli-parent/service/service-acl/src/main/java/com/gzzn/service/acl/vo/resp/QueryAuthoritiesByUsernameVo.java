package com.gzzn.service.acl.vo.resp;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author eric
 * @date 2022/10/5 13:57
 **/
@Data
public class QueryAuthoritiesByUsernameVo {
    private String id;
    private String username;
    private String password;
    private Boolean locked; //true为锁定，false为不锁定
    //做权限的去重
    private Set<String> permits = new HashSet<>();
}
