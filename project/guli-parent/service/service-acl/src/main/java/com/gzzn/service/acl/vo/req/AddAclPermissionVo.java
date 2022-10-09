package com.gzzn.service.acl.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
public class AddAclPermissionVo {

    @NotNull(message = "字段不能为空")
    @Min(value = 0,message = "最少为0")
    @Max(value = 1,message = "最大值为1")
    @ApiModelProperty(value = "类型，0为菜单，1为按钮",required = true)
    private Short type;

    @ApiModelProperty(value = "上级菜单")
    private String parentId;

    @NotNull(message = "字段不能为空")
    @ApiModelProperty(value = "名称",required = true)
    private String title;

    @ApiModelProperty(value = "权限编码")
    private String permit;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "组件path")
    private String path;

    @ApiModelProperty(value = "组件name")
    private String name;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "隐藏组件")
    private Boolean hidden;

    @NotNull(message = "字段不能为空")
    @ApiModelProperty(value = "状态，0为正常，1为禁用",required = true)
    @Min(value = 0,message = "最少为0")
    @Max(value = 1,message = "最大值为1")
    private Short status;

    @ApiModelProperty(value = "排序",required = true)
    private Integer sort;
}
