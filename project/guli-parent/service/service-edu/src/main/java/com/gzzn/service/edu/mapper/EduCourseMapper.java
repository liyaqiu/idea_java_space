package com.gzzn.service.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzzn.service.edu.entity.EduCourseEntity;
import com.gzzn.service.edu.entity.EduSubjectEntity;
import com.gzzn.service.edu.vo.resp.QueryEduCourseDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lyq
 * @date 2021/12/5 12:32
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourseEntity> {

    /*@Select("select * from edu_course where id = #{uid}")*/
    @Select("SELECT cou.id,cou.title,cou.lesson_num,cou.price,cou.cover,\n" +
            "\t\t\t\ttea.name as teacher_name,\n" +
            "\t\t\t\tonesub.title as one_subject,\n" +
            "\t\t\t\ttwosub.title as two_subject\n" +
            "FROM edu_course cou\n" +
            "\t\t\tLEFT JOIN  edu_teacher tea on tea.id = cou.teacher_id\n" +
            "\t\t\tLEFT JOIN  edu_subject onesub on onesub.id = cou.subject_parent_id\n" +
            "\t\t\tLEFT JOIN  edu_subject twosub on twosub.id = cou.subject_id\n" +
            "WHERE cou.id = #{uid}")
    QueryEduCourseDetailVo selectEduCourseDetail(String uid);
}
