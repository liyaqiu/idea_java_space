package com.write._02指定写入列;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class ExcelDO {
    @ExcelProperty(value = "名字", index = 0)
    private String string;
    @ExcelProperty(value = "日期", index = 1)
    private Date date;
    //这里设置3 会导致第二列空的
    @ExcelProperty(value = "数字", index = 3)
    private Integer number;
}
