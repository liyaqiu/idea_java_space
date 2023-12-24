package com.bean;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/2/26 9:23
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Goods{
    String name ="default name";
    String count = "default count";
}
