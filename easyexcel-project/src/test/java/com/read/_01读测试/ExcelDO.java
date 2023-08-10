package com.read._01读测试;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class ExcelDO {
    private String string;
    private Date date;
    private Integer number;
}