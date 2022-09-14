package com.gzzn.service.ucenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author eric
 * @date 2022/9/2 22:54
 **/
@Data
@TableName("ucenter_member")
public class UcenterMemberEntity {
   @TableId(type = IdType.ASSIGN_ID)
   private String id;
   private String openid;
   private String mobile;
   private String password;
   private String nickname;
   private Short sex;
   private Short age;
   private String avatar;
   private String sign;
   private Short isDisabled;//是否禁用 1（true）已禁用，  0（false）未禁用
   private Short isDeleted;
   private Date gmtCreate;
   private Date gmtModified;
}
