package com.write._03格式转换输出;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class ExcelDO {
    @ExcelProperty(value = "名字", converter = CustomConverter.class)
    private String string;
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "日期")
    private Date date;
    //用百分比表示
    @NumberFormat("#%")
    @ExcelProperty(value = "数字")
    private Integer number;
}
