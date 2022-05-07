package com.mycache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lyq
 * @date 2022/3/10 18:35
 */
@Getter
@Setter
@ToString
@AllArgsConstructor

public class CacheUser implements Serializable {
    String id;
    String name;
    String age;
}
