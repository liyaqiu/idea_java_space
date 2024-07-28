package com.fm._1数据类型;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Data
public class Person {
    private String name = "eric";
    private int age = 18;
    private double price = 17.677;
    private String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private Date birthday = new Date();
    private boolean health = true;
    private People people = new People("lyq-people",17);
    private Map<String,String> map = new HashMap<>();
    {
        map.put("name","lyq-map");
        map.put("age","12");
    }
    private String[] arrStr = new String[]{"eric1","eric2","eric3"};
    private List<String> arrList = new ArrayList<>();
    {
        arrList.add("lyq1");
        arrList.add("lyq2");
        arrList.add("lyq3");
    }
    private String[][] arrs = new String[][]{{"eric11","eric22","eric33"},{"lyq11","lyq22","lyq33"}};

    private People[] peoples = new People[]{new People("lyq-people",16),new People("lyq-people",17),new People("lyq-people",18)};
    @Data
    @AllArgsConstructor
    public class People{
        private String name;
        private int age;
    }

}
