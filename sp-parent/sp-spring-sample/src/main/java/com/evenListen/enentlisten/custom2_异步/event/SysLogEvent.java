package com.evenListen.enentlisten.custom2_异步.event;



import com.evenListen.enentlisten.custom2_异步.dto.OptLogDTO;
import org.springframework.context.ApplicationEvent;
/**
 * 定义系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {
    public SysLogEvent(OptLogDTO optLogDTO) {
        super(optLogDTO);
    }
}