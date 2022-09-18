package com.gzzn.service.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzzn.service.edu.entity.EduTeacherEntity;
import com.gzzn.service.edu.vo.resp.TeacherMetricVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @author lyq
 * @date 2021/12/5 12:32
 */
@Mapper
public interface EduTeacherMapper extends BaseMapper<EduTeacherEntity> {
    //<script>select * from user <if test=\"id !=null \">where id = #{id} </if></script>
    @Select("<script> " +
            "SELECT career,count(0) as count from edu_teacher where 1=1 " +
                "<if test='startDate != null'>" +
                    "and gmt_create &gt;= #{startDate}" +
                "</if> " +
                "<if test='endDate != null'>" +
                    "and gmt_create &lt;= #{endDate}" +
                "</if> " +
            "GROUP BY career" +
            "</script>")
    public List<TeacherMetricVo> selectMetricList(Date startDate, Date endDate);
}
