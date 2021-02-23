package com.dahua.clxx.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResult {
    /**
     * 结果码
     */
    private String code;

    /**
     * 结果码描述
     */
    private String msg;

}