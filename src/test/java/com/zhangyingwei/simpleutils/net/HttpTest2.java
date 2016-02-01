package com.zhangyingwei.simpleutils.net;

import java.util.HashMap;
import java.util.Map;

import com.zhangyingwei.simpleutils.net.common.HttpResult;
import com.zhangyingwei.simpleutils.util.UtilFactory;

public class HttpTest2 {
	public static void main(String[] args) {
		String url = "http://172.16.100.33:8080/c3-crm-serv/member/elBisMemberHouses/retrieveMemberOrCarInfoByHouseId";
		Map houseinfo = new HashMap();
		houseinfo.put("houseId", "94");
		Map map = new HashMap();
		map.put("requestData", houseinfo.toString());
		HttpResult result = UtilFactory.getHttpUtil().doGet(url,map);
		System.out.println(result.getResultStr());
	}
}
