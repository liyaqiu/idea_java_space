package com.mycache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lyq
 * @date 2022/3/10 18:35
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class CacheUser {
    String id;
    String name;
    String age;
}
