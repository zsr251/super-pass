package com.javasoso.pass.constant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 通用返回值
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultModel<T> implements Serializable {
	/**
	 * 成功
	 */
	public static final String RESULT_SUCCESS = "1";
	/**
	 * 失败
	 */
	public static final String RESULT_ERROR = "2";
	/**
	 * 登录失效
	 */
	public static final String RESULT_AUTH_INVALID = "3";
	/**
	 * 访问频繁
	 */
	public static final String RESULT_NOPERMISSION_LIMIT = "4";
	/**
	 * 不支持
	 */
	public static final String RESULT_NOT_SUPPORTED = "5";

	private static final long serialVersionUID = 1L;

	private String code;

	private String msg;

	private T data;
	public ResultModel(){

	}
	public ResultModel(String c, String m){
		this.code = c;
		this.msg = m;
	}
	
	@JsonProperty("c")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@JsonProperty("m")
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@JsonProperty("d")
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
