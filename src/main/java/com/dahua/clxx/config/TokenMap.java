package com.dahua.clxx.config;

import com.dahua.clxx.pojo.Token;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenMap {
    //token,用户信息
    public static Map<String, Token> map = new ConcurrentHashMap<>();

    //dssToken,添加删除权限时需要
    public static String dssToken = "";
}
