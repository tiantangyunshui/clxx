package com.dahua.clxx.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 自定义业务异常
 */
@Data
@AllArgsConstructor
public class BusinessRuntimeException extends RuntimeException {
    /**
     * 结果码
     */
    private String code = "200";

    /**
     * 结果码描述
     */
    private String msg;

}