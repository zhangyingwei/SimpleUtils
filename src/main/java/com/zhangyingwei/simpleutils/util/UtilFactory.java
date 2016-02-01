package com.zhangyingwei.simpleutils.util;

import com.zhangyingwei.simpleutils.date.DateUtil;
import com.zhangyingwei.simpleutils.encode.DESUtil;
import com.zhangyingwei.simpleutils.encode.MD5Util;
import com.zhangyingwei.simpleutils.file.FileUtil;
import com.zhangyingwei.simpleutils.net.FtpUtil;
import com.zhangyingwei.simpleutils.net.HttpUtil;
import com.zhangyingwei.simpleutils.string.StringUtil;

public class UtilFactory {
	
	public static StringUtil getStringUtil(){
		return StringUtil.getInstance();
	}
	
	public static HttpUtil getHttpUtil(){
		return HttpUtil.getInstance();
	}
	
	public static FtpUtil getFtpUtil(){
		return FtpUtil.getInstance();
	}
	
	
	public static FileUtil getFileUtil(){
		return FileUtil.getInstance();
	}
	
	public static DateUtil getDateUtil(){
		return DateUtil.getInstance();
	}
	
	public static EncodeUtil getEncodeUtil(){
		return EncodeUtil.getInstance();
	}
	
}
