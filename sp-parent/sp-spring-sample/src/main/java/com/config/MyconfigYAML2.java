package com.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author lyq
 * @date 2022/2/26 23:59
 * @EnableConfigurationProperties({MyconfigYAML2.class}) 利用开关来统一管理配置
 */
@Getter
@Setter
@ToString
@ConfigurationProperties("myconfig")
public class MyconfigYAML2 {
    String name;
    String age;
    String other;
    @DurationUnit(ChronoUnit.DAYS)
    Duration duration; //1d 1h 1s赋值
    @DataSizeUnit(DataUnit.BYTES)
    DataSize dataSize;//10kb  10mb 10gb
}
