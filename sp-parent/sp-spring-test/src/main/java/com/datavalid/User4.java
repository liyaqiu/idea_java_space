package com.datavalid;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author lyq
 * @date 2022/2/26 23:59
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties("myconfig")
@Validated //jsr303贵干，接口 javax.validation 实现 hibernate-validator
public class User4 {
    String name;
    @NotNull
    @Max(value = 20,message = "最大值为20")
    @Min(value = 10,message = "最少为10")
    String age;
    String max;
}
