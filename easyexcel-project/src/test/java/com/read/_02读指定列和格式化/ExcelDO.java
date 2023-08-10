package com.read._02读指定列和格式化;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ExcelDO {
    @ExcelProperty(index = 2)
    private Integer number;

    @ExcelProperty(value = "名字", converter = CustomConverter.class)
    private String string;
    //名字做匹配
    @ExcelProperty("日期")
    //这里用string 去接日期才能格式化。我想接收年月日格式
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private String date;
    //private Date date;
}