package com.zhangyingwei.simpleutils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.zhangyingwei.simpleutils.util.UtilFactory;

public class DateUtil {
	private DateUtil(){}
	
	private static class DateUtilHandler{
		private static DateUtil dateUtil = new DateUtil();
	}
	
	public static DateUtil getInstance(){
		return DateUtilHandler.dateUtil;
	}
	
	public static Date date = null;

	public static DateFormat dateFormat = null;

	public static Calendar calendar = null;

	
	/**
	 * 功能描述：格式化日期
	 * 
	 * 2015/11/18 :</br>
	 * 这里还没有适配类似Wed Apr 11 16:18:42 +0800 2012这种格式的转换，因为没找到一个清晰的办法&_&
	 * @see "Wed Apr 11 16:18:42 +0800 2012"
	 * 
	 * @param dateStr String 字符型日期
	 * @param format String 格式
	 * @return Date 日期
	 */
	public  Date parseDate(String dateStr, String format) {
		if(UtilFactory.getStringUtil().isNull(dateStr)){
			return null;
		}else if(UtilFactory.getStringUtil().isNull(format)){
			dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale("ENGLISH", "CHINA"));
			try {
				date = dateFormat.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			dateFormat = new SimpleDateFormat(format);
			try {
				date = dateFormat.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * 功能描述：格式化输出日期
	 * 
	 * @param date Date 日期
	 * @param format String 格式
	 * @return 返回字符型日期
	 */
	public String format(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				dateFormat = new SimpleDateFormat(format);
				result = dateFormat.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 功能描述：yyyy/MM/dd
	 * 
	 * @param date Date 日期
	 * @return
	 */
	public  String format(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：返回年份
	 * 
	 * @param date Date 日期
	 * @return 返回年份
	 */
	public  int getYear(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 功能描述：返回月份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public  int getMonth(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public  int getDay(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public  int getHour(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public  int getMinute(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date Date 日期
	 * @return 返回秒钟
	 */
	public  int getSecond(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫秒
	 * 
	 * @param date 日期
	 * @return 返回毫秒
	 */
	public  long getMillis(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * 功能描述：返回字符型日期
	 * 
	 * @param date 日期
	 * @return 返回字符型日期 yyyy/MM/dd 格式
	 */
	public  String getDate(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：返回字符型时间
	 * 
	 * @param date Date 日期
	 * @return 返回字符型时间 HH:mm:ss 格式
	 */
	public  String getTime(Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 功能描述：返回字符型日期时间
	 * 
	 * @param date Date 日期
	 * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
	 */
	public  String getDateTime(Date date) {
		return format(date, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 功能描述：日期相加
	 * 
	 * @param date Date 日期
	 * @param day int 天数
	 * @return 返回相加后的日期
	 */
	public  Date addDate(Date date, int day) {
		calendar = Calendar.getInstance();
		long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/**
	 * 功能描述：日期相减
	 * 
	 * @param date Date 日期
	 * @param date1 Date 日期
	 * @return 返回相减后的日期
	 */
	public  int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 功能描述：取得指定月份的第一天
	 * 
	 * @param strdate String 字符型日期
	 * @return String yyyy-MM-dd 格式
	 */
	public  String getMonthBegin(String strdate,String format) {
		date = parseDate(strdate,format);
		if(date==null){
			return "";
		}
		this.calendar = Calendar.getInstance();
		this.calendar.setTime(date);
		this.calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format(this.calendar.getTime(), format);
	}

	/**
	 * 功能描述：取得指定月份的最后一天
	 * 
	 * @param strdate String 字符型日期
	 * @return String 日期字符串 yyyy-MM-dd格式
	 */
	public  String getMonthEnd(String strdate,String format) {
		date = parseDate(getMonthBegin(strdate,format),format);
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime(),format);
	}

	/**
	 * 功能描述：常用的格式化日期
	 * 
	 * @param date Date 日期
	 * @return String 日期字符串 yyyy-MM-dd格式
	 */
	public  String formatDate(Date date,String format) {
		return formatDateByFormat(date, format);
	}

	/**
	 * 功能描述：以指定的格式来格式化日期
	 * 
	 * @param date Date 日期
	 * @param format String 格式
	 * @return String 日期字符串
	 */
	public  String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 获取当前日期
	 * @return Date
	 */
	public Date getCurrentDate(){
		return new Date();
	}
	
	/**
	 * 获取当前日期
	 * @return String
	 */
	public String getCurrentDate(String format){
		return this.format(this.getCurrentDate(), format);
	}
	
	/**
	 * 获取当前Day
	 * @return int
	 */
	public int getCurrentDay(){
		return this.getDay(this.getCurrentDate());
	}
	
	/**
	 * 获取当前月份
	 * @return int
	 */
	public int getCurrentMonth(){
		return this.getMonth(this.getCurrentDate());
	}
	
	/**
	 * 获取当前年份
	 * @return int
	 */
	public int getChrrentYear(){
		return this.getYear(this.getCurrentDate());
	}
	
	/**
	 * 获取当前分钟数
	 * @return int
	 */
	public int getCurrentMinute(){
		return this.getMinute(this.getCurrentDate());
	}
	
	/**
	 * 获取当前毫秒数
	 * @return long
	 */
	public long getCurrentMillis(){
		return this.getMillis(this.getCurrentDate());
	}
	
	/**
	 * 获取当前小时数
	 * @return int
	 */
	public int getCurrentHour(){
		return this.getHour(this.getCurrentDate());
	}
	
	/**
	 * 获取当前秒数
	 * @return int
	 */
	public int getCurrentSecond(){
		return this.getSecond(this.getCurrentDate());
	}
	
	
}
