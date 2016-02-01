package com.zhangyingwei.simpleutils.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.zhangyingwei.simpleutils.entity.HttpCookie;
import com.zhangyingwei.simpleutils.entity.HttpParams;
import com.zhangyingwei.simpleutils.net.common.HttpResult;


public class HttpUtil{
	
	private static Logger logger = Logger.getLogger(HttpUtil.class);
	
	private HttpUtil() {}

	private static class HttpUtilHandler {
		private static HttpUtil httpUtil = new HttpUtil();
	}

	public static HttpUtil getInstance() {
		return HttpUtilHandler.httpUtil;
	}
	
	/**
	 * ����cookie
	 */
	private Map<String,HttpCookie> cookies = new HashMap<String,HttpCookie>();
	public HttpCookie getCookie(String host) {
		return this.cookies.get(host);
	}
	public void addCookie(String host,Header[] cookieinfo) {
		if(cookies.containsKey(host)){
			return;
		}else{
			this.cookies.put(host, new HttpCookie(cookieinfo));
		}
	}
	
	/**
	 * ����get����
	 * @param url
	 * @param params
	 * @param cookie
	 * @return
	 */
	public HttpResult doGet(String url, Map params, HttpCookie cookie) {
		logger.info("doGet");
		HttpResult result = new HttpResult();
		HttpClient client = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(this.initGetUrl(url, params));
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		//��������ͷ����Ϣ
		if(cookie!=null&&cookie.isEmpty()){
			httpget.addHeader(HttpParams.REQ_COOKIE_KEY,cookie.buildCookieStr());
		}else{
			if(this.cookies.containsKey(httpget.getURI().getHost())){
				httpget.addHeader(HttpParams.REQ_COOKIE_KEY,this.getCookie(httpget.getURI().getHost()).buildCookieStr());
			}
		}
		httpget.addHeader(HttpParams.REQ_ACCEPT_KEY, HttpParams.REQ_ACCEPT_VALUE);
		httpget.addHeader(HttpParams.REQ_ACCEPT_ENCODING_KEY, HttpParams.REQ_ACCEPT_ENCODING_VALUE);
		httpget.addHeader(HttpParams.REQ_ACCEPT_LANGUAGE_KEY, HttpParams.REQ_ACCEPT_LANGUAGE_VALUE);
		httpget.addHeader(HttpParams.REQ_CACHE_CONTROL_KEY, HttpParams.REQ_CACHE_CONTROL_VALUE);
		httpget.addHeader(HttpParams.REQ_CONNECTION_KEY, HttpParams.REQ_CONNECTION_VALUE);
		httpget.addHeader(HttpParams.REQ_USER_AGENT_KEY,HttpParams.REQ_USER_AGENT_VALUE_WINDOWS);
		try {
			response = (CloseableHttpResponse) client.execute(httpget);
			result.setStatusCode(response.getStatusLine().getStatusCode());//����״̬��
			logger.info(response.getStatusLine().getStatusCode());
			entity = response.getEntity();
			result.setResultStr(EntityUtils.toString(entity));//����ֵ
			this.addCookie(httpget.getURI().getHost(), response.getHeaders(HttpParams.RSP_COOKIE_KEY));
			result.setCookies(new HttpCookie(response.getHeaders(HttpParams.RSP_COOKIE_KEY)));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public HttpResult doGet(String url, Map params) {
		return this.doGet(url, params, null);
	}
	public HttpResult doGet(String url) {
		return this.doGet(url, null);
	}
	
	/**
	 * ����post����
	 * @param url
	 * @param params
	 * @param cookie
	 * @return
	 */
	public HttpResult doPost(String url, Map params, HttpCookie cookie){
		logger.info("doPost");
		HttpResult result = new HttpResult();
		HttpClient client = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		//��������ͷ����Ϣ
		/**
		 * �����������
		 */
		HttpEntity postParams = this.initPostParam(params);
		if(postParams!=null){
			httppost.setEntity(postParams);
			logger.info(postParams);
		}
		/**
		 * ����cookie��Ϣ��
		 * �������cookie��Ϣ��Ѵ����cookie��Ϣ��ӵ�ͷ��Ϣ��
		 * ���û�д���cookie��Ϣ���ڻ����cookie��Ϣ�в��Ҷ�Ӧ��cookie��Ϣ�����û���ҵ����򲻴�cookie��Ϣ
		 */
		if(cookie!=null&&cookie.isEmpty()){
			httppost.addHeader(HttpParams.REQ_COOKIE_KEY,cookie.buildCookieStr());
		}else{
			if(this.cookies.containsKey(httppost.getURI().getHost())){
				httppost.addHeader(HttpParams.REQ_COOKIE_KEY,this.getCookie(httppost.getURI().getHost()).buildCookieStr());
			}
		}
		httppost.addHeader(HttpParams.REQ_ACCEPT_KEY, HttpParams.REQ_ACCEPT_VALUE);
		httppost.addHeader(HttpParams.REQ_ACCEPT_ENCODING_KEY, HttpParams.REQ_ACCEPT_ENCODING_VALUE);
		httppost.addHeader(HttpParams.REQ_ACCEPT_LANGUAGE_KEY, HttpParams.REQ_ACCEPT_LANGUAGE_VALUE);
		httppost.addHeader(HttpParams.REQ_CACHE_CONTROL_KEY, HttpParams.REQ_CACHE_CONTROL_VALUE);
		httppost.addHeader(HttpParams.REQ_CONNECTION_KEY, HttpParams.REQ_CONNECTION_VALUE);
		httppost.addHeader(HttpParams.REQ_USER_AGENT_KEY,HttpParams.REQ_USER_AGENT_VALUE_WINDOWS);
		try {
			response = (CloseableHttpResponse) client.execute(httppost);
			result.setStatusCode(response.getStatusLine().getStatusCode());//״̬��
			logger.info(response.getStatusLine().getStatusCode());
			entity = response.getEntity();
			result.setResultStr(EntityUtils.toString(entity));//����ֵ
			this.addCookie(httppost.getURI().getHost(), response.getHeaders(HttpParams.RSP_COOKIE_KEY));
			result.setCookies(new HttpCookie(response.getHeaders(HttpParams.RSP_COOKIE_KEY)));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public HttpResult doPost(String url, Map params) {
		return this.doPost(url, params, null);
	}
	public HttpResult doPost(String url) {
		return this.doPost(url, null);
	}

	/**
	 * ��ʼ��get�����url
	 * @param url
	 * @param params
	 * @return
	 */
	private String initGetUrl(String url,Map params){
		if(params==null){
			logger.info(url);
			return url;
		}else if(!(params instanceof Map)){
			logger.info("params is not map");
			logger.info(url);
			return url;
		}else{
			StringBuffer realurl = new StringBuffer(url).append("?");
			for(Object key:params.keySet()){
				realurl.append(key).append("=").append(URLEncoder.encode((String) params.get(key))).append("&");
			}
			logger.info(realurl.toString());
			return realurl.toString();
		}
	}
	
	/**
	 * ��ʼ��post�������
	 * @param params
	 * @return HttpEntity
	 */
	private HttpEntity initPostParam(Map params){
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		if(!(params instanceof Map)){
			logger.info("params is not map");
			return null;
		}
		for(Object obj : params.keySet()){
			parameters.add(new BasicNameValuePair(obj.toString(), params.get(obj).toString()));
		}
		UrlEncodedFormEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(parameters);
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}
}
