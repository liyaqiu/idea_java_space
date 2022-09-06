package com.gzzn.service.edu.easyexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eric
 * @date 2022/9/6 23:49
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectModel {
    @ExcelProperty(value = "一级分类",index = 0)
    private String oneSubject;
    @ExcelProperty(value = "二级分类",index = 1)
    private String twoSubject;
}
