/**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2016
 *
 * *******************************************************************
 */
package com.dahua.clxx.agbox;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求参数
 * @author 50898
 * @Date 2018/9/31
 */
@Data
public class RequestMessage implements Serializable {

	private static final long serialVersionUID = -7014755228955474900L;
	
	/**
	 * 第三方请求参数说明:
	 * jsonrpc	指定 JSON-RPC 协议版本的字符串，必须准确写为“2.0”
	 * method	所要调用方法名称的字符串
	 * params	调用方法所需要的结构化参数值可忽略
	 * id	              客户端的唯一标识 id，值必须包含一个字符串、数值或 NU
	 *          被认定为是一个通知。若为数值则不应该包含小数。使用小数是不确定性的，因为许
	 *          多十进制小数不能精准的表达为二进制小数。
	 */
	
	private String jsonrpc;
	
	private String method;
	
	private Object params;
	
	private String id;
	
	public RequestMessage(){
		this.jsonrpc = "2.0";
		this.id = "DH1001";
	}

	public RequestMessage(String method) {
		this.jsonrpc = "2.0";
		this.id = "DH1001";
		this.method = method;
	}
}
