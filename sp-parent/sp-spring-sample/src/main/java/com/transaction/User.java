package com.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lyq
 * @date 2022/3/3 2:54
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String age;

}
