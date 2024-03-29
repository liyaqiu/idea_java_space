package com.gzzn.service.common.exception.handler;

import com.gzzn.service.common.exception.FileUploadException;
import com.gzzn.service.utils.Res;
import com.gzzn.service.utils.ResCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author lyq
 * @date 2021/12/7 11:12
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

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
        //String msg = e.getBindingResult().getFieldError().getDefaultMessage()
        return Res.fail()
                .setMessage(e.getBindingResult().getAllErrors().stream().findFirst().get().toString())
                .setCode(ResCode.DATA_VALIDATION_FAIL);
    }

    @ExceptionHandler(FileUploadException.class)
    public Res doFileUploadException(FileUploadException e){
        log.error("",e);
        return Res.fail().setMessage("文件上传失败").setCode(ResCode.FILE_UPLOAD_FAIL);
    }

}
