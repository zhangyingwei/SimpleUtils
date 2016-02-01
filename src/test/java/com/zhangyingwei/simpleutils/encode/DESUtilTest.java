package com.zhangyingwei.simpleutils.encode;

import java.util.Arrays;

import com.zhangyingwei.simpleutils.util.UtilFactory;

public class DESUtilTest {
	public static void main(String[] args) throws Exception {
		String key = "zhangyingwei.com";
		String value = "zhangyw_001@126.com";
		String encodestr = UtilFactory.getEncodeUtil().encodeDES(value, key);
		System.out.println(encodestr);
		String decodestr = UtilFactory.getEncodeUtil().decodeDES(encodestr, key);
		System.out.println(decodestr);
    }
}
