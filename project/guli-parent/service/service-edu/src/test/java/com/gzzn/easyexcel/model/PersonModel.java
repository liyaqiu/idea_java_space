package com.gzzn.easyexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eric
 * @date 2022/9/6 21:17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonModel {
    @ExcelProperty(value = "姓名",index = 0)
    private String name;
    @ExcelProperty(value = "年龄",index = 1)
    private Integer age;
}
