package com.zhangyingwei.simpleutils.net;

import java.util.HashMap;
import java.util.Map;
import com.zhangyingwei.simpleutils.entity.HttpCookie;
import com.zhangyingwei.simpleutils.net.common.HttpResult;
import com.zhangyingwei.simpleutils.util.UtilFactory;

public class HttpUtilTest {
	public static void main(String[] args) {
		String qurl = "http://weixin.sogou.com/weixin?query=%E5%95%8A";
		
		HttpCookie cookie = new HttpCookie();
		Map<String, String> map = new HashMap();
		map.put("ABTEST", "8|1448002355|v1");
//		map.put("SNUID", "F8FACD94E9EFCE10A666918FEAB96610");
		cookie.add(map);
		
		Map pm = new HashMap();
		pm.put("query", "%E5%95%8A");
		
		HttpResult result = UtilFactory.getHttpUtil().doGet(qurl, pm);
//		System.out.println(result.getStatusCode());
//		System.out.println(result.getResultStr());
//		
//		System.out.println(UtilFactory.getHttpUtil().doGet(qurl,pm,result.getCookies()).getCookies());
	}
}
