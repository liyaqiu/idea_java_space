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
public class FrontendPageQueryEduCourseVo {

    @ApiModelProperty(value = "一级分类")
    private String subjectParentId;

    @ApiModelProperty(value = "二级分类")
    private String subjectId;


    @ApiModelProperty(value = "价格排序,asc升序 desc降序",allowableValues = "asc,desc")
    @Pattern(regexp = "^asc|desc$", message = "只允许这2个asc,desc")
    private String priceSort;

    @ApiModelProperty(value = "销量排序,asc升序 desc降序",allowableValues = "asc,desc")
    @Pattern(regexp = "^asc|desc$", message = "只允许这2个asc,desc")
    private String saleSort;
}
