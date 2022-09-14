package com.gzzn.service.ucenter.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author eric
 * @date 2022/9/14 14:53
 **/
@Data
public class RegisterVo {
    @NotNull
    private String nickname;

    @NotNull
    @Length(min = 2,max = 11,message = "长度为2-11")
    @ApiModelProperty(value = "手机号，长度为2-11")
    private String mobile;

    @NotNull
    @Length(min = 4,max = 4,message = "长度4")
    @ApiModelProperty(value = "验证码，长度4")
    private String code;

    @NotNull
    @Length(min = 2,max = 16,message = "长度为2-16")
    @ApiModelProperty(value = "密码，长度为2-11")
    private String password;
}
