package com.dahua.clxx.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dahua.clxx.exception.BusinessRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		String token = request.getHeader("token");
		log.info("token:{},method:{}",token,request.getMethod());
		if("OPTIONS".equals(request.getMethod())||(token!=null && TokenMap.map.containsKey(token))){
			return true;
		}else {
			throw new BusinessRuntimeException(401,"未授权");
		}
	}
}