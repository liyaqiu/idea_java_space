package com.gzzn.service.gateway.securitybak.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author eric
 * @date 2022/10/4 17:25
 **/
@Data
public class AclUserEntity {
    private String id;
    private String userName;
    private String passwd;
    private Integer sort;
    private Date gmtCreate;
    private Date gmtModified;
}
