package com.config;

/**
 * @author eric
 * @date 2022/9/22 17:21
 **/

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/2/26 23:59
 * @EnableConfigurationProperties({MyconfigYAML2.class})   利用开关来同意管理配置
 */
@Component
@EnableConfigurationProperties({MyconfigYAML2.class})
public class EnbaleConfig {
}
