package com.gzzn.service.acl.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author eric
 * @date 2022/9/3 23:00
 **/
@Data
@ApiModel
public class UpdateAclRoleVo {

    @ApiModelProperty(value = "角色id",required = true)
    @NotBlank(message = "字段不能为空")
    private String id;

    @NotBlank(message = "字段不能为空")
    @ApiModelProperty(value = "角色名称",required = true)
    private String name;

    @NotBlank(message = "字段不能为空")
    @ApiModelProperty(value = "角色编码",required = true)
    private String code;

    @NotBlank(message = "字段不能为空")
    @ApiModelProperty(value = "角色描述",required = true)
    private String remark;

    @NotNull(message = "字段不能为空")
    @ApiModelProperty(value = "角色状态，0为正常，1为禁用",required = true)
    @Min(value = 0,message = "最少为0")
    @Max(value = 1,message = "最大值为1")
    private Short status;

}
