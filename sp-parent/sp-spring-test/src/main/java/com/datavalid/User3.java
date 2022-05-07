package com.datavalid;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author lyq
 * @date 2022/3/8 11:22
 */
@Getter
@Setter
@ToString
public class User3 {
    @NotNull(message = "name不可以为空")
    String name;
    @Max(value = 20,message = "最大值为20")
    @Min(value = 10,message = "最少为10")
    int age;
}
