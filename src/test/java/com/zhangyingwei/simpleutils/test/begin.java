package com.zhangyingwei.simpleutils.test;

import com.zhangyingwei.simpleutils.util.UtilFactory;

public class begin {
	public static void main(String[] args) throws Exception {
		String chinese = new String("�Ұ������찲����������");
		System.out.println(chinese);
		String html = UtilFactory.getStringUtil().getChineseBiginCharacter(chinese, false);
		System.out.println(html);
	}
}
