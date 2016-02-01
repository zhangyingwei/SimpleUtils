package com.zhangyingwei.simpleutils.test;

import com.zhangyingwei.simpleutils.util.UtilFactory;

public class begin {
	public static void main(String[] args) throws Exception {
		String chinese = new String("我爱北京天安门魑魅魍魉");
		System.out.println(chinese);
		String html = UtilFactory.getStringUtil().getChineseBiginCharacter(chinese, false);
		System.out.println(html);
	}
}
