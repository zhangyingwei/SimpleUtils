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
	 * �Ƚ������ַ����Ĵ�С
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
	 * ��ȡһ���ַ�����codeֵ
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
	 * �Ƚ����������ַ�����code��С
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
	 * ��ȡһ�κ��������к��ֵ�ƴ������ĸ
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
	 * ��ȡһ�κ����е�һ�����ֵ�ƴ������ĸ
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
	 * ��ȡһ�κ����е�һ�����ֵ�ƴ������ĸ
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
	 * ��ȡһ�κ����е�һ�����ֵ�ƴ������ĸ
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String getChineseBiginCharacter(String chinese,boolean isfirst,Charset charset) throws UnsupportedEncodingException{
		return this.getChineseBiginCharacter(new String(chinese.getBytes(charset),"gbk"), isfirst);
	}
	

	/**
	 * �滻�ַ��������ܹ���HTMLҳ����ֱ����ʾ(�滻˫���ź�С�ں�)
	 * 
	 * @param str
	 *            String ԭʼ�ַ���
	 * @return String �滻����ַ���
	 */
	private String htmlEncode(String str) {
		if (str == null) {
			return null;
		}
		return str.replaceAll("<", "&lt;").replaceAll("\"", "&quot;");
	}
	
	/**
	 * �滻�ַ��������ܹ���HTMLҳ����ֱ����ʾ������jsע��
	 * @param str ԭʼ�ַ���
	 * @param simple �Ƿ���м��滻
	 * true �滻˫������С�ں�
	 * false �������������ַ�
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
	 * �滻�ַ��������������ת����ԭʼ�루�滻��˫���ź�С�ںţ�
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
	 * �滻�ַ��������������ת����ԭʼ��
	 * @param str ԭʼ�ַ���
	 * @param simple �Ƿ��Ǽ򵥽���
	 * true �滻��˫���ź�С�ں�
	 * false �滻����������ŵ�ת���
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
	 * ������������ҳ����ֱ����ʾ�ı����ݣ��滻С�ںţ��ո񣬻س���TAB
	 * </br>
	 * str.replaceAll("<", "&lt;")</br>
	 *	.replaceAll(" ","&nbsp;")</br>
	 *	.replaceAll("\r\n", _BR)</br>
	 *	.replaceAll("\n", _BR)</br>
	 *	.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");</br>
	 * @param str String ԭʼ�ַ���
	 * @return String �滻����ַ���
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
	 * ��������������ָ���ֽڳ��ȵ��ַ���</br>
	 * �����ڼ��...
	 * 
	 * @param str String �ַ���
	 * @param length int ָ������
	 * @return String ���ص��ַ���
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
	 * �����������ж��Ƿ�Ϊ����
	 * �ж�һ���ַ����Ƿ����ת��Ϊ����
	 * 
	 * @param str ������ַ���
	 * @return ����������true,���򷵻�false
	 */
	public boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * �ж��Ƿ�Ϊ������������double��float
	 * 
	 * @param str ������ַ���
	 * @return �Ǹ���������true,���򷵻�false
	 */
	public boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * �ж��ǲ��ǺϷ��ַ� c Ҫ�жϵ��ַ�
	 */
	public boolean isLetter(String str) {
		if (str == null || str.length() < 0) {
			return false;
		}
		Pattern pattern = Pattern.compile("[\\w\\.-_]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * ��ָ�����ַ�������ȡEmail content ָ�����ַ���
	 * 
	 * @param content
	 * @return
	 */
	public String getEmailFromString(String content) {
		String email = null;
		if (content == null || content.length() < 1) {
			return email;
		}
		// �ҳ�����@
		int beginPos;
		int i;
		String token = "@";
		String preHalf = "";
		String sufHalf = "";

		beginPos = content.indexOf(token);
		if (beginPos > -1) {
			// ǰ��ɨ��
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
			// ����ɨ��
			i = beginPos + 1;
			while (i < content.length()) {
				s = content.substring(i, i + 1);
				if (isLetter(s))
					sufHalf = sufHalf + s;
				else
					break;
				i++;
			}
			// �жϺϷ���
			email = preHalf + "@" + sufHalf;
			if (isEmail(email)) {
				return email;
			}
		}
		return null;
	}

	/**
	 * �����������ж�������ַ����Ƿ����Email��ʽ.
	 * 
	 * @param str
	 *            ������ַ���
	 * @return ��Email��ʽ����true,���򷵻�false
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
	 * �����������ж�������ַ����Ƿ�Ϊ������
	 * 
	 * @param str
	 *            ������ַ���
	 * @return ����Ǵ����ַ���true,���򷵻�false
	 */
	public boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * �����������Ƿ�Ϊ�հ�,����null��""
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNull(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * �����������ж��Ƿ�Ϊ����
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
	 * ���������������ת�ɴ�д
	 * 
	 * @param str �����ַ���
	 * @return String �����ת���ɴ�д����ַ���
	 */
	public String hangeToBig(String str) {
		double value;
		try {
			value = Double.parseDouble(str.trim());
		} catch (Exception e) {
			return null;
		}
		char[] hunit = { 'ʰ', '��', 'Ǫ' }; // ����λ�ñ�ʾ
		char[] vunit = { '��', '��' }; // ������ʾ
		char[] digit = { '��', 'Ҽ', '��', '��', '��', '��', '½', '��', '��', '��' }; // ���ֱ�ʾ
		long midVal = (long) (value * 100); // ת��������
		String valStr = String.valueOf(midVal); // ת�����ַ���

		String head = valStr.substring(0, valStr.length() - 2); // ȡ��������
		String rail = valStr.substring(valStr.length() - 2); // ȡС������

		String prefix = ""; // ��������ת���Ľ��
		String suffix = ""; // С������ת���Ľ��
		// ����С����������
		if (rail.equals("00")) { // ���С������Ϊ0
			suffix = "��";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "��"
					+ digit[rail.charAt(1) - '0'] + "��"; // ����ѽǷ�ת������
		}
		// ����С����ǰ�����
		char[] chDig = head.toCharArray(); // ����������ת�����ַ�����
		char zero = '0'; // ��־'0'��ʾ���ֹ�0
		byte zeroSerNum = 0; // ��������0�Ĵ���
		for (int i = 0; i < chDig.length; i++) { // ѭ������ÿ������
			int idx = (chDig.length - i - 1) % 4; // ȡ����λ��
			int vidx = (chDig.length - i - 1) / 4; // ȡ��λ��
			if (chDig[i] == '0') { // �����ǰ�ַ���0
				zeroSerNum++; // ����0��������
				if (zero == '0') { // ��־
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0; // ����0��������
			if (zero != '0') { // �����־��Ϊ0,�����,������,��ʲô��
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // ת�������ֱ�ʾ
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // �ν���λ��Ӧ�ü��϶�������,��
			}
		}

		if (prefix.length() > 0)
			prefix += 'Բ'; // ����������ִ���,����Բ������
		return prefix + suffix; // ������ȷ��ʾ
	}

	/**
	 * ����������ȥ���ַ������ظ������ַ���
	 * 
	 * @param str ԭ�ַ�������������ַ������ÿո�����Ա�ʾ���ַ���
	 * @return String ����ȥ���ظ����ַ�������ַ���
	 */
	private String removeSameString(String str) {
		Set mLinkedSet = new LinkedHashSet();// set���ϵ����������Ӽ��������ظ�
		String[] strArray = str.split(" ");// ���ݿո�(������ʽ)�ָ��ַ���
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
	 * �������������������ַ�
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
	 * �����������ж��ǲ��ǺϷ����ֻ�����
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
	 * ���������������������ַ�
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
