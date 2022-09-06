package com.gzzn.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.edu.easyexcel.listener.ReadExcelListener;
import com.gzzn.service.edu.easyexcel.model.SubjectModel;
import com.gzzn.service.edu.entity.EduSubjectEntity;
import com.gzzn.service.edu.entity.EduTeacherEntity;
import com.gzzn.service.edu.mapper.EduSubjectMapper;
import com.gzzn.service.edu.mapper.EduTeacherMapper;
import com.gzzn.service.edu.service.EduSubjectService;
import com.gzzn.service.edu.service.EduTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.invoke.ConstantCallSite;
import java.util.Date;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
@Slf4j
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubjectEntity> implements EduSubjectService {

    @Autowired
    EduSubjectMapper eduSubjectMapper;

    @Override
    @Transactional
    public void addEduSubject(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), SubjectModel.class,new ReadExcelListener(new ReadExcelListener.ReadExceCallback(){
                @Override
                public void readLine(SubjectModel subjectModel) {
                    EduSubjectEntity oneSubject = selectSubjectByTitle(subjectModel.getOneSubject());
                    if(oneSubject==null){
                        oneSubject = new EduSubjectEntity();
                        modelTransEntity(oneSubject,subjectModel.getOneSubject());
                        eduSubjectMapper.insert(oneSubject);
                    }

                    EduSubjectEntity twoSubject = selectSubjectByTitle(subjectModel.getTwoSubject());
                    if(twoSubject==null){
                        twoSubject = new EduSubjectEntity();
                        modelTransEntity(twoSubject,subjectModel.getTwoSubject());
                        twoSubject.setParentId(oneSubject.getId());
                        eduSubjectMapper.insert(twoSubject);
                    }
                }
            })).sheet().doRead();
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }



    /*模型转换实体*/
    private void modelTransEntity(EduSubjectEntity entity,String title){
        entity.setTitle(title);
        entity.setSort(0);
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());
    }

    /*根据title查询课程*/
    private EduSubjectEntity selectSubjectByTitle(String title){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("title", title);
        EduSubjectEntity entity = eduSubjectMapper.selectOne(wrapper);
        return entity;
    }
}
