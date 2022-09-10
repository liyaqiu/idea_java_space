package com.gzzn.service.edu.vo.resp;

import com.gzzn.service.edu.entity.EduVideoEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author eric
 * @date 2022/9/9 16:58
 **/
@Data
public class QueryChapterVo {
    private String id;
    private String courseId;
    private String title;
    private Integer sort;
    private Date gmtCreate;
    private Date gmtModified;
    private List<EduVideoEntity> eduVideoList;
}
