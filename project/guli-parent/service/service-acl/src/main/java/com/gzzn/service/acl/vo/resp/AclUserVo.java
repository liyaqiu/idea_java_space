package com.gzzn.service.acl.vo.resp;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author eric
 * @date 2022/10/10 17:45
 **/
@Data
public class AclUserVo {
    private String id;
    private String username;
    private String nickName;
    private String avatar;
    private String email;
    private String phone;
    private Boolean locked;
    private List<String> roleNames;
    private Date gmtCreate;
    private Date gmtModified;
}
