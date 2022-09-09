package com.gzzn.service.edu.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author eric
 * @date 2022/9/3 23:00
 **/
@Data
@ApiModel
public class UpdateEduCourseVo {
    @ApiModelProperty(value = "课程id")
    private String id;
    @ApiModelProperty(value = "讲师id")
    private String teacherId;
    @ApiModelProperty(value = "二级分类")
    private String subjectId;
    @ApiModelProperty(value = "一级分类")
    private String subjectParentId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;
    @ApiModelProperty(value = "封面图片")
    private String cover;
    @ApiModelProperty(value = "描述")
    private String description;

}
