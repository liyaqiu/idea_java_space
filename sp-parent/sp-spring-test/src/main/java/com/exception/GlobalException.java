package com.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lyq
 * @date 2021/12/7 11:12
 */
//@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doException1(Exception exception){
        log.error("", exception);
        return new Result(exception.getMessage(),Result.FAIL);
    }
    /*@ExceptionHandler(RuntimeException.class)
    public String doException2(RuntimeException exception){
        log.error("", exception);
        return "redirect:/hello.html";
    }*/


}
