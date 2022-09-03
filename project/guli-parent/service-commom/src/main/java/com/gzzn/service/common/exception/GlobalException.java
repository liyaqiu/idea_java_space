package com.gzzn.service.common.exception;

import com.gzzn.service.common.utils.Res;
import com.gzzn.service.common.utils.ResCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lyq
 * @date 2021/12/7 11:12
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public Res doException(Exception e){
        log.error("",e);
        return Res.fail().setMessage(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Res doRuntimeException(RuntimeException e){
        log.error("",e);
        return Res.fail().setMessage(e.getMessage()).setCode(ResCode.BUSINESS_FAIL);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Res doMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("",e);
        return Res.fail().setMessage(e.getMessage()).setCode(ResCode.DATA_VALIDATION_FAIL);
    }

}
