package com.gzzn.service.edu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author eric
 * @date 2022/9/3 23:00
 **/
@Data
@ApiModel
public class AddEduTeacherDto {
    /*@NotNull
    @Length(min = 5)
    @NotNull
    @Max(value = 20,message = "最大值为20")
    @Min(value = 10,message = "最少为10")*/

    @ApiModelProperty(value = "讲师姓名",required = true)
    @NotNull(message = "字段不能为空")
    @Length(min = 5,message = "最小长度为5")
    private String name;

    @NotNull(message = "字段不能为空")
    @ApiModelProperty(value = "讲师简介",required = true)
    private String intro;

    @NotNull(message = "字段不能为空")
    @ApiModelProperty(value = "讲师资历,一句话说明讲师",required = true)
    private String career;

    @ApiModelProperty(value = "讲师头像",required = false)
    private String avatar;
}
