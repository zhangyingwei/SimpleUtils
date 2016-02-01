package com.zhangyingwei.simpleutils.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;

public class HttpCookie {
	private List<String> headers = new ArrayList<String>();
	
	
	public HttpCookie(){}
	public HttpCookie(Header[] cookies){
		for(int i = 0;i<cookies.length;i++){
			this.headers.add(cookies[i].getValue());
		}
	}
	
	public String buildCookieStr(){
		StringBuilder cookiestr = new StringBuilder();
		for(String cookie:headers){
			cookiestr.append(cookie).append(";");
		}
		return cookiestr.toString();
	}
	
	public void add(Map headeValue){
		headers.add(this.initCookieValue(headeValue));
	}
	
	private String initCookieValue(Map headeValue){
		StringBuffer result = new StringBuffer();
		for(Object key:headeValue.keySet()){
			result.append(key).append("=").append(headeValue.get(key)).append(",");
		}
		return result.toString();
	}
	
	@Override
	public String toString() {
		return Arrays.toString(this.headers.toArray());
	}
	
	public boolean isEmpty(){
		return this.headers==null||this.headers.size()==0;
	}
	
}
