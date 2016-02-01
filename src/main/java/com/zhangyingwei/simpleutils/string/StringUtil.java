package com.zhangyingwei.simpleutils.string;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public StringUtil(){
		
	}
	
	private static class StringUtilHandler{
		public static StringUtil stringUtil = new StringUtil();
	}
	
	public static StringUtil getInstance(){
		return StringUtilHandler.stringUtil;
	}
	
	private String _FromEncode_ = "GBK";
	private String _ToEncode_ = "GBK";

	/**
	 * 比较两个字符串的大小
	 * @param str1
	 * @param str2
	 * @return
	 */
	public int compare(String str1, String str2) {
		int result = 0;
		String m_s1 = null;
		String m_s2 = null;
		try {
			m_s1 = new String(str1.getBytes(_FromEncode_), _ToEncode_);
			m_s2 = new String(str2.getBytes(_FromEncode_), _ToEncode_);
		} catch (Exception e) {
			return str1.compareTo(str2);
		}
		result = ChineseCompareTo(m_s1, m_s2);
		return result;
	}

	/**
	 * 获取一个字符串的code值
	 * @param s
	 * @return
	 */
	public int getCharCode(String s) {
		if (s == null && s.equals(""))
			return -1;
		byte b[] = s.getBytes();
		int value = 0;
		for (int i = 0; i < b.length && i <= 2; i++)
			value = value * 100 + b[i];

		return value;
	}

	
	/**
	 * 比较两个汉语字符串的code大小
	 * @param s1
	 * @param s2
	 * @return
	 */
	public int ChineseCompareTo(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();
		int n = Math.min(len1, len2);
		for (int i = 0; i < n; i++) {
			int s1_code = getCharCode(s1.charAt(i) + "");
			int s2_code = getCharCode(s2.charAt(i) + "");
			if (s1_code * s2_code < 0)
				return Math.min(s1_code, s2_code);
			if (s1_code != s2_code)
				return s1_code - s2_code;
		}

		return len1 - len2;
	}

	/**
	 * 获取一段汉字中所有汉字的拼音首字母
	 * 
	 * @param res
	 * @return
	 */
	private String getAllBeginCharacter(String res) {
		String a = res;
		String result = "";
		for (int i = 0; i < a.length(); i++) {
			String current = a.substring(i, i + 1);
			if (compare(current, "\u554A") < 0)
				result = result + current;
			else if (compare(current, "\u554A") >= 0
					&& compare(current, "\u5EA7") <= 0)
				if (compare(current, "\u531D") >= 0)
					result = result + "z";
				else if (compare(current, "\u538B") >= 0)
					result = result + "y";
				else if (compare(current, "\u6614") >= 0)
					result = result + "x";
				else if (compare(current, "\u6316") >= 0)
					result = result + "w";
				else if (compare(current, "\u584C") >= 0)
					result = result + "t";
				else if (compare(current, "\u6492") >= 0)
					result = result + "s";
				else if (compare(current, "\u7136") >= 0)
					result = result + "r";
				else if (compare(current, "\u671F") >= 0)
					result = result + "q";
				else if (compare(current, "\u556A") >= 0)
					result = result + "p";
				else if (compare(current, "\u54E6") >= 0)
					result = result + "o";
				else if (compare(current, "\u62FF") >= 0)
					result = result + "n";
				else if (compare(current, "\u5988") >= 0)
					result = result + "m";
				else if (compare(current, "\u5783") >= 0)
					result = result + "l";
				else if (compare(current, "\u5580") >= 0)
					result = result + "k";
				else if (compare(current, "\u51FB") > 0)
					result = result + "j";
				else if (compare(current, "\u54C8") >= 0)
					result = result + "h";
				else if (compare(current, "\u5676") >= 0)
					result = result + "g";
				else if (compare(current, "\u53D1") >= 0)
					result = result + "f";
				else if (compare(current, "\u86FE") >= 0)
					result = result + "e";
				else if (compare(current, "\u642D") >= 0)
					result = result + "d";
				else if (compare(current, "\u64E6") >= 0)
					result = result + "c";
				else if (compare(current, "\u82AD") >= 0)
					result = result + "b";
				else if (compare(current, "\u554A") >= 0)
					result = result + "a";
		}

		return result;
	}

	/**
	 * 获取一段汉字中第一个汉字的拼音首字母
	 * 
	 * @param str
	 * @return
	 */
	private String getFirstBeginCharacter(String str) {
		char a = str.charAt(0);
		char aa[] = { a };
		String sss = new String(aa);
		if (Character.isDigit(aa[0]))
			sss = "data";
		else if (a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z')
			sss = "character";
		else
			sss = getAllBeginCharacter(sss);
		return sss;
	}
	
	/**
	 * 获取一段汉字中第一个汉字的拼音首字母
	 * 
	 * @param str
	 * @return
	 */
	public String getChineseBiginCharacter(String chinese,boolean isfirst){
		if(this.isNull(chinese)){
			return "";
		}else if(isfirst){
			return this.getFirstBeginCharacter(chinese);
		}else{
			return this.getAllBeginCharacter(chinese);
		}
	}
	/**
	 * 获取一段汉字中第一个汉字的拼音首字母
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String getChineseBiginCharacter(String chinese,boolean isfirst,Charset charset) throws UnsupportedEncodingException{
		return this.getChineseBiginCharacter(new String(chinese.getBytes(charset),"gbk"), isfirst);
	}
	

	/**
	 * 替换字符串，能能够在HTML页面上直接显示(替换双引号和小于号)
	 * 
	 * @param str
	 *            String 原始字符串
	 * @return String 替换后的字符串
	 */
	private String htmlEncode(String str) {
		if (str == null) {
			return null;
		}
		return str.replaceAll("<", "&lt;").replaceAll("\"", "&quot;");
	}
	
	/**
	 * 替换字符串，能能够在HTML页面上直接显示，避免js注入
	 * @param str 原始字符串
	 * @param simple 是否进行简单替换
	 * true 替换双引号与小于号
	 * false 过滤所有特殊字符
	 * @return
	 */
	public String htmlEncode(String str,boolean simple){
		if(this.isNull(str)){
			return "";
		}else if(simple){
			return this.htmlEncode(str);
		}else{
			return this.encoding(str);
		}
	}

	/**
	 * 替换字符串，将被编码的转换成原始码（替换成双引号和小于号）
	 * 
	 * @param str String
	 * @return String
	 */
	private String htmlDecode(String str) {
		if (str == null) {
			return null;
		}
		return str.replaceAll("&quot;", "\"").replaceAll("&lt;", "<").replaceAll("&gt;", ">");
	}
	
	/**
	 * 替换字符串，将被编码的转换成原始码
	 * @param str 原始字符串
	 * @param simple 是否是简单解码
	 * true 替换成双引号和小于号
	 * false 替换所有特殊符号的转译符
	 * @return
	 */
	public String htmlDecode(String str,boolean simple){
		if(this.isNull(str)){
			return "";
		}else if(simple){
			return this.htmlDecode(str);
		}else{
			return this.decoding(str);
		}
	}

	private final String _BR = "<br/>";

	/**
	 * 功能描述：在页面上直接显示文本内容，替换小于号，空格，回车，TAB
	 * </br>
	 * str.replaceAll("<", "&lt;")</br>
	 *	.replaceAll(" ","&nbsp;")</br>
	 *	.replaceAll("\r\n", _BR)</br>
	 *	.replaceAll("\n", _BR)</br>
	 *	.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");</br>
	 * @param str String 原始字符串
	 * @return String 替换后的字符串
	 */
	public String htmlShow(String str) {
		if (str == null) {
			return null;
		}
		return str.replaceAll("<", "&lt;")
		.replaceAll(" ","&nbsp;")
		.replaceAll("\r\n", _BR)
		.replaceAll("\n", _BR)
		.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
	}

	/**
	 * 功能描述：返回指定字节长度的字符串</br>
	 * 类似于简介...
	 * 
	 * @param str String 字符串
	 * @param length int 指定长度
	 * @return String 返回的字符串
	 */
	public String toLength(String str, int length) {
		if (str == null) {
			return null;
		}
		if (length <= 0) {
			return "";
		}
		try {
			if (str.getBytes("GBK").length <= length) {
				return str;
			}
		} catch (Exception e) {
		}
		StringBuffer buff = new StringBuffer();

		int index = 0;
		char c;
		length -= 3;
		while (length > 0) {
			c = str.charAt(index);
			if (c < 128) {
				length--;
			} else {
				length--;
				length--;
			}
			buff.append(c);
			index++;
		}
		buff.append("...");
		return buff.toString();
	}

	/**
	 * 功能描述：判断是否为整数
	 * 判断一个字符串是否可以转换为整数
	 * 
	 * @param str 传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为浮点数，包括double和float
	 * 
	 * @param str 传入的字符串
	 * @return 是浮点数返回true,否则返回false
	 */
	public boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是不是合法字符 c 要判断的字符
	 */
	public boolean isLetter(String str) {
		if (str == null || str.length() < 0) {
			return false;
		}
		Pattern pattern = Pattern.compile("[\\w\\.-_]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 从指定的字符串中提取Email content 指定的字符串
	 * 
	 * @param content
	 * @return
	 */
	public String getEmailFromString(String content) {
		String email = null;
		if (content == null || content.length() < 1) {
			return email;
		}
		// 找出含有@
		int beginPos;
		int i;
		String token = "@";
		String preHalf = "";
		String sufHalf = "";

		beginPos = content.indexOf(token);
		if (beginPos > -1) {
			// 前项扫描
			String s = null;
			i = beginPos;
			while (i > 0) {
				s = content.substring(i - 1, i);
				if (isLetter(s))
					preHalf = s + preHalf;
				else
					break;
				i--;
			}
			// 后项扫描
			i = beginPos + 1;
			while (i < content.length()) {
				s = content.substring(i, i + 1);
				if (isLetter(s))
					sufHalf = sufHalf + s;
				else
					break;
				i++;
			}
			// 判断合法性
			email = preHalf + "@" + sufHalf;
			if (isEmail(email)) {
				return email;
			}
		}
		return null;
	}

	/**
	 * 功能描述：判断输入的字符串是否符合Email样式.
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是Email样式返回true,否则返回false
	 */
	public boolean isEmail(String email) {
		if (email == null || email.length() < 1 || email.length() > 256) {
			return false;
		}
		Pattern pattern = Pattern
				.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(email).matches();
	}

	/**
	 * 功能描述：判断输入的字符串是否为纯汉字
	 * 
	 * @param str
	 *            传入的字符窜
	 * @return 如果是纯汉字返回true,否则返回false
	 */
	public boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 功能描述：是否为空白,包括null和""
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNull(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 功能描述：判断是否为质数
	 * 
	 * @param x
	 * @return
	 */
	public boolean isPrime(int x) {
		if (x <= 7) {
			if (x == 2 || x == 3 || x == 5 || x == 7)
				return true;
		}
		int c = 7;
		if (x % 2 == 0)
			return false;
		if (x % 3 == 0)
			return false;
		if (x % 5 == 0)
			return false;
		int end = (int) Math.sqrt(x);
		while (c <= end) {
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 6;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 6;
		}
		return true;
	}

	/**
	 * 功能描述：人民币转成大写
	 * 
	 * @param str 数字字符串
	 * @return String 人民币转换成大写后的字符串
	 */
	public String hangeToBig(String str) {
		double value;
		try {
			value = Double.parseDouble(str.trim());
		} catch (Exception e) {
			return null;
		}
		char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
		char[] vunit = { '万', '亿' }; // 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
		long midVal = (long) (value * 100); // 转化成整形
		String valStr = String.valueOf(midVal); // 转化成字符串

		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
		String rail = valStr.substring(valStr.length() - 2); // 取小数部分

		String prefix = ""; // 整数部分转化的结果
		String suffix = ""; // 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) { // 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角"
					+ digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4; // 取段内位置
			int vidx = (chDig.length - i - 1) / 4; // 取段位置
			if (chDig[i] == '0') { // 如果当前字符是0
				zeroSerNum++; // 连续0次数递增
				if (zero == '0') { // 标志
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0; // 连续0次数清零
			if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // 转化该数字表示
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
			}
		}

		if (prefix.length() > 0)
			prefix += '圆'; // 如果整数部分存在,则有圆的字样
		return prefix + suffix; // 返回正确表示
	}

	/**
	 * 功能描述：去掉字符串中重复的子字符串
	 * 
	 * @param str 原字符串，如果有子字符串则用空格隔开以表示子字符串
	 * @return String 返回去掉重复子字符串后的字符串
	 */
	private String removeSameString(String str) {
		Set mLinkedSet = new LinkedHashSet();// set集合的特征：其子集不可以重复
		String[] strArray = str.split(" ");// 根据空格(正则表达式)分割字符串
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < strArray.length; i++) {
			if (!mLinkedSet.contains(strArray[i])) {
				mLinkedSet.add(strArray[i]);
				sb.append(strArray[i] + " ");
			}
		}
		System.out.println(mLinkedSet);
		return sb.toString();
	}

	/**
	 * 功能描述：过滤特殊字符
	 * 
	 * @param src
	 * @return
	 */
	private String encoding(String src) {
		if (src == null)
			return "";
		StringBuilder result = new StringBuilder();
		if (src != null) {
			src = src.trim();
			for (int pos = 0; pos < src.length(); pos++) {
				switch (src.charAt(pos)) {
				case '\"':
					result.append("&quot;");
					break;
				case '<':
					result.append("&lt;");
					break;
				case '>':
					result.append("&gt;");
					break;
				case '\'':
					result.append("&apos;");
					break;
				case '&':
					result.append("&amp;");
					break;
				case '%':
					result.append("&pc;");
					break;
				case '_':
					result.append("&ul;");
					break;
				case '#':
					result.append("&shap;");
					break;
				case '?':
					result.append("&ques;");
					break;
				default:
					result.append(src.charAt(pos));
					break;
				}
			}
		}
		return result.toString();
	}

	/**
	 * 功能描述：判断是不是合法的手机号码
	 * 
	 * @param handset
	 * @return boolean
	 */
	public boolean isPhoneNum(String phonenum) {
		try {
			String regex = "^1[\\d]{10}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(phonenum);
			return matcher.matches();

		} catch (RuntimeException e) {
			return false;
		}
	}

	/**
	 * 功能描述：反过滤特殊字符
	 * 
	 * @param src
	 * @return
	 */
	private String decoding(String src) {
		if (src == null)
			return "";
		String result = src;
		result = result.replace("&quot;", "\"").replace("&apos;", "\'");
		result = result.replace("&lt;", "<").replace("&gt;", ">");
		result = result.replace("&amp;", "&");
		result = result.replace("&pc;", "%").replace("&ul", "_");
		result = result.replace("&shap;", "#").replace("&ques", "?");
		return result;
	}
	
}
