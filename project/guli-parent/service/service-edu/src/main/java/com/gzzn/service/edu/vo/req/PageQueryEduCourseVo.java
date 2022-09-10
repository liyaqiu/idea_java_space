package com.gzzn.service.edu.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author eric
 * @date 2022/9/4 2:08
 **/
@Data
@ApiModel
public class PageQueryEduCourseVo {

    @ApiModelProperty(value = "开始时间 yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @ApiModelProperty(value = "结束时间 yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布",allowableValues = "Draft,Normal")
    @Pattern(regexp = "^Draft|Normal$", message = "只允许这2个Draft,Normal")
    private String status;

}
