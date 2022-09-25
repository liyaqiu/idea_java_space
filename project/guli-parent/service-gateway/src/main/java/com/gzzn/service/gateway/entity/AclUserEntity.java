package com.gzzn.service.gateway.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author eric
 * @date 2022/9/2 22:54
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
