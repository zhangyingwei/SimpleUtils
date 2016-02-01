package com.zhangyingwei.simpleutils.net;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zhangyingwei.simpleutils.entity.HttpCookie;
import com.zhangyingwei.simpleutils.net.common.HttpResult;
import com.zhangyingwei.simpleutils.util.UtilFactory;

public class HttpUtilTest2 {
	public static void main(String[] args) {
		String url = "http://www.tuling123.com/openapi/api";
		Map params = new HashMap();
		params.put("key", "ee937e2cc2a71cd93e60fed652212a70");
		params.put("info", "今天天气怎么样");
		params.put("loc", "北京市中关村");
		HttpResult result = UtilFactory.getHttpUtil().doPost(url, params);
		System.out.println(result);
	}
}
