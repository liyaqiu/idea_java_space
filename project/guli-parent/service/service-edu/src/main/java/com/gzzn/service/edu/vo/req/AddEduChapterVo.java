package com.gzzn.service.edu.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author eric
 * @date 2022/9/3 23:00
 **/
@Data
@ApiModel
public class AddEduChapterVo {
    @ApiModelProperty(value = "课程id")
    private String courseId;
    @ApiModelProperty(value = "章节标题")
    private String title;
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
