package com.gzzn.service.edu.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author eric
 * @date 2022/9/3 23:00
 **/
@Data
@ApiModel
public class AddEduVideoVo {
    @ApiModelProperty(value = "课程id")
    private String courseId;
    @ApiModelProperty(value = "章节id")
    private String chapterId;
    @ApiModelProperty(value = "小节标题")
    private String title;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "是否可以试听：0免费 1收费")
    private Integer isFree;
    @ApiModelProperty(value = "视频id")
    private String videoSourceId;
    @ApiModelProperty(value = "视频名")
    private String videoOriginalName;
}
