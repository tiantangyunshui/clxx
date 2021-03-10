package com.dahua.clxx.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义业务异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BusinessRuntimeException extends RuntimeException {
    /**
     * 结果码
     */
    private int code = 200;

    /**
     * 结果码描述
     */
    private String msg;

}