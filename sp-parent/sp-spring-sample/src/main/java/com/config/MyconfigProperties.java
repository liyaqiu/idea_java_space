package com.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author lyq
 * @date 2022/2/26 23:59
 */

@PropertySource("classpath:MyConfig.properties")
@PropertySource("classpath:MyConfig2.properties")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyconfigProperties {
    @Value("${pname}")
    String pname;
    @Value("${page}")
    String page;
    @Value("${psize:没赋值}")
    String psize;
    @Value("${myconfig.path:不存在}")
    String other;

}
