package com.dahua.clxx.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局Controller层异常处理类
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionResolver {

    /**
     * 处理所有不可知异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult handleException(Exception e) {
        log.error("其他不可知异常堆栈：{}", ErrorUtil.err(e));
        return new ApiResult("500",e.getMessage());
    }

    /**
     * 处理所有自定义业务异常
     */
    @ExceptionHandler(BusinessRuntimeException.class)
    @ResponseBody
    public ApiResult handleOpdRuntimeException(BusinessRuntimeException e) {
        log.error("自定义异常堆栈：{}",ErrorUtil.err(e));
        return new ApiResult(e.getCode(),e.getMsg());
    }
}