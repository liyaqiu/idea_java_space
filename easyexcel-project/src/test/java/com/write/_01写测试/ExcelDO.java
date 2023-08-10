package com.write._01写测试;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class ExcelDO {
    @ExcelProperty("名字")
    private String string;
    @ExcelProperty("日期")
    private Date date;
    @ExcelProperty("数字")
    private Integer number;
}
