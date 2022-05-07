package com.sentinel.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author lyq
 * @date 2022/1/21 23:42
 */
@Component
@Slf4j
public class CustomSentinelException implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        log.info("{sentinel异常}");
       /* PrintWriter out = httpServletResponse.getWriter();
        out.print("Blocked by Sentinel (flow limiting)");
        out.flush();
        out.close();*/
        throw e;
    }
}
