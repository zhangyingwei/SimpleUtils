package com.zhangyingwei.simpleutils.net.common;

import com.zhangyingwei.simpleutils.entity.HttpCookie;

public class HttpResult {
	private String resultStr;
	private HttpCookie cookies;
	private Integer statusCode;
	public String getResultStr() {
		return resultStr;
	}
	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}
	public HttpCookie getCookies() {
		return cookies;
	}
	public void setCookies(HttpCookie cookies) {
		this.cookies = cookies;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	@Override
	public String toString() {
		return "HttpResult [resultStr=" + resultStr + ", cookies=" + cookies
				+ ", statusCode=" + statusCode + "]";
	}
}
