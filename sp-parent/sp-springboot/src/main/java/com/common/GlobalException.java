package com.common;

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
        log.error("", exception);
        return new Result(exception.getMessage(),Result.FAIL);
    }
    /*@ExceptionHandler(RuntimeException.class)
    public Result doException2(RuntimeException exception){
        log.error("", exception);
        return new Result(exception.getMessage(),Result.FAIL);
    }*/
}
