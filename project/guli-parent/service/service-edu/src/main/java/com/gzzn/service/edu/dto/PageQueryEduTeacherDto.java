package com.gzzn.service.edu.dto;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author eric
 * @date 2022/9/4 2:08
 **/
@Data
@ApiModel
public class PageQueryEduTeacherDto {

    @ApiModelProperty(value = "开始时间，使用东八区时间",allowableValues = "Sun Sep 04 02:24:01 CST 2022")
    private Date beginTime;

    @ApiModelProperty(value = "结束时间,使用东八区时间",allowableValues="Sun Sep 04 02:24:01 CST 2022")
    private Date endTime;

    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师",allowableValues="1,2")
    private Integer level;
}
