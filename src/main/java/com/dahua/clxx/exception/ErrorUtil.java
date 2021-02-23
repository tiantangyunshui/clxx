package com.dahua.clxx.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 输出异常堆栈信息
 */
public class ErrorUtil {

    public static String err(Throwable e){
        String error;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            e.printStackTrace(ps);
            error = baos.toString();
            baos.close();
            ps.close();
        } catch (Exception e1) {
            error = e.toString();
        }
        return error;
    }
}