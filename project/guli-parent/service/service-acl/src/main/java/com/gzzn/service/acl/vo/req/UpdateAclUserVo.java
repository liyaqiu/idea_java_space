package com.gzzn.service.acl.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author eric
 * @date 2022/9/3 23:00
 **/
@Data
@ApiModel
public class UpdateAclUserVo {

    @ApiModelProperty(value = "用户id",required = true)
    @NotBlank(message = "字段不能为空")
    private String id;

    @NotBlank(message = "字段不能为空")
    @Length(min = 5,max = 18,message = "长度为5-18")
    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @URL
    @ApiModelProperty(value = "头像")
    private String avatar;

    @Email
    @ApiModelProperty(value = "email")
    private String email;

    @Pattern(regexp = "^1[3-9]\\d{9}$")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @NotNull(message = "字段不能为空")
    @ApiModelProperty(value = "是否锁定",required = true)
    private Boolean locked;

}
