package com.gzzn.service.edu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author eric
 * @date 2022/9/3 23:00
 **/
@Data
@ApiModel
public class UpdateEduTeacherDto {

    @ApiModelProperty(value = "讲师id",required = true)
    @NotNull(message = "字段不能为空")
    private String id;

    @ApiModelProperty(value = "讲师姓名")
    @Length(min = 5,message = "最小长度为5")
    private String name;

    @ApiModelProperty(value = "讲师简介")
    private String intro;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String career;

    @ApiModelProperty(value = "讲师头像")
    private String avatar;
}
