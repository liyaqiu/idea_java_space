package com.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lyq
 * @date 2021/12/7 11:12
 */
@RestControllerAdvice //方式1
//@ControllerAdvice //方式2
//@ControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GlobalException {

    @ExceptionHandler({Exception.class,RuntimeException.class})
    public Result 方式1_2(Exception exception, HttpServletResponse response, HttpServletRequest request){
        log.error("", exception);
        if(exception instanceof Exception){
            return new Result(exception.getMessage(),Result.FAIL);
        }else if(exception instanceof RuntimeException){
            RuntimeException r = (RuntimeException) exception;
            return new Result(r.getMessage(),Result.FAIL);
        }
        return new Result(exception.getMessage(),Result.FAIL);
    }

    /*@ExceptionHandler(Exception.class)
    @ResponseBody
    public Result 方式1_2(Exception exception){
        log.error("", exception);
        return new Result(exception.getMessage(),Result.FAIL);
    }*/

    /*@ExceptionHandler(RuntimeException.class)
    public String 方式2(RuntimeException exception){
        log.error("", exception);
        return "redirect:/hello.html";
    }*/


}
