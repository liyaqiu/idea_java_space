package com.controller;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public enum Person{

    A(),B("eric","18");

    @Getter
    @Setter
    private String name;
    private String age;
}


