package com.fm._2指令;

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
    private People people = new People("lyq-people",17);
    private List<String> arrList = new ArrayList<>();
    {
        arrList.add("lyq1");
        arrList.add("lyq2");
        arrList.add("lyq3");
    }
    @Data
    @AllArgsConstructor
    public class People{
        private String name;
        private int age;
    }


}
