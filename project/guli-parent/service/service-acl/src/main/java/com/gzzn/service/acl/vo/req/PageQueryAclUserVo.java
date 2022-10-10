package com.gzzn.service.acl.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author eric
 * @date 2022/9/4 2:08
 **/
@Data
@ApiModel
public class PageQueryAclUserVo {

    @ApiModelProperty(value = "开始时间 yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @ApiModelProperty(value = "结束时间 yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    @ApiModelProperty(value = "用户名")
    private String username;
}
