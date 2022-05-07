package com.sentinel.rule;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 做sentinel授权规则
 * @author lyq
 * @date 2022/1/21 23:22
 */
@Component
@Slf4j
public class HeardOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        String origin = httpServletRequest.getHeader("origin");
        log.info("origin:{}",origin);
        if(StringUtils.isEmpty(origin)){
            return "blank";
        }
        return origin;
    }
}
