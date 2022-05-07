package com.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lyq
 * @date 2021/12/7 11:12
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public Result doException1(Exception exception){
        log.error("全局异常捕获", exception);
        return new Result(exception.getMessage(),Result.FAIL);
    }
    /**
     * 扑捉sentinel抛出来的异常
     * */
    @ExceptionHandler(BlockException.class)
    public Result doException2(BlockException e) {
        if (e instanceof FlowException) {
            log.error("限流异常");
            return new Result("限流异常", Result.SENTINEL_FAIL);
        } else if (e instanceof ParamFlowException) {
            log.error("热点参数限流异常");
            return new Result("热点参数限流异常", Result.SENTINEL_FAIL);
        } else if (e instanceof DegradeException) {
            log.error("降级异常");
            return new Result("降级异常", Result.SENTINEL_FAIL);
        } else if (e instanceof AuthorityException) {
            log.error("授权规则异常");
            return new Result("授权规则异常", Result.SENTINEL_FAIL);
        }else{
            log.error("未知的异常");
            return new Result("未知的异常", Result.SENTINEL_FAIL);
        }

    }
}
