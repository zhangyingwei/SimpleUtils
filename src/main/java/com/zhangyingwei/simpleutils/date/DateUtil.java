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
	 * ������������ʽ������
	 * 
	 * 2015/11/18 :</br>
	 * ���ﻹû����������Wed Apr 11 16:18:42 +0800 2012���ָ�ʽ��ת������Ϊû�ҵ�һ�������İ취&_&
	 * @see "Wed Apr 11 16:18:42 +0800 2012"
	 * 
	 * @param dateStr String �ַ�������
	 * @param format String ��ʽ
	 * @return Date ����
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
	 * ������������ʽ���������
	 * 
	 * @param date Date ����
	 * @param format String ��ʽ
	 * @return �����ַ�������
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
	 * ����������yyyy/MM/dd
	 * 
	 * @param date Date ����
	 * @return
	 */
	public  String format(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * �����������������
	 * 
	 * @param date Date ����
	 * @return �������
	 */
	public  int getYear(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * ���������������·�
	 * 
	 * @param date
	 *            Date ����
	 * @return �����·�
	 */
	public  int getMonth(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * ���������������շ�
	 * 
	 * @param date
	 *            Date ����
	 * @return �����շ�
	 */
	public  int getDay(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ��������������Сʱ
	 * 
	 * @param date
	 *            ����
	 * @return ����Сʱ
	 */
	public  int getHour(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * �������������ط���
	 * 
	 * @param date
	 *            ����
	 * @return ���ط���
	 */
	public  int getMinute(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * ��������
	 * 
	 * @param date Date ����
	 * @return ��������
	 */
	public  int getSecond(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * �������������غ���
	 * 
	 * @param date ����
	 * @return ���غ���
	 */
	public  long getMillis(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * ���������������ַ�������
	 * 
	 * @param date ����
	 * @return �����ַ������� yyyy/MM/dd ��ʽ
	 */
	public  String getDate(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * ���������������ַ���ʱ��
	 * 
	 * @param date Date ����
	 * @return �����ַ���ʱ�� HH:mm:ss ��ʽ
	 */
	public  String getTime(Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * ���������������ַ�������ʱ��
	 * 
	 * @param date Date ����
	 * @return �����ַ�������ʱ�� yyyy/MM/dd HH:mm:ss ��ʽ
	 */
	public  String getDateTime(Date date) {
		return format(date, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * �����������������
	 * 
	 * @param date Date ����
	 * @param day int ����
	 * @return ������Ӻ������
	 */
	public  Date addDate(Date date, int day) {
		calendar = Calendar.getInstance();
		long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/**
	 * �����������������
	 * 
	 * @param date Date ����
	 * @param date1 Date ����
	 * @return ��������������
	 */
	public  int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * ����������ȡ��ָ���·ݵĵ�һ��
	 * 
	 * @param strdate String �ַ�������
	 * @return String yyyy-MM-dd ��ʽ
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
	 * ����������ȡ��ָ���·ݵ����һ��
	 * 
	 * @param strdate String �ַ�������
	 * @return String �����ַ��� yyyy-MM-dd��ʽ
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
	 * �������������õĸ�ʽ������
	 * 
	 * @param date Date ����
	 * @return String �����ַ��� yyyy-MM-dd��ʽ
	 */
	public  String formatDate(Date date,String format) {
		return formatDateByFormat(date, format);
	}

	/**
	 * ������������ָ���ĸ�ʽ����ʽ������
	 * 
	 * @param date Date ����
	 * @param format String ��ʽ
	 * @return String �����ַ���
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
	 * ��ȡ��ǰ����
	 * @return Date
	 */
	public Date getCurrentDate(){
		return new Date();
	}
	
	/**
	 * ��ȡ��ǰ����
	 * @return String
	 */
	public String getCurrentDate(String format){
		return this.format(this.getCurrentDate(), format);
	}
	
	/**
	 * ��ȡ��ǰDay
	 * @return int
	 */
	public int getCurrentDay(){
		return this.getDay(this.getCurrentDate());
	}
	
	/**
	 * ��ȡ��ǰ�·�
	 * @return int
	 */
	public int getCurrentMonth(){
		return this.getMonth(this.getCurrentDate());
	}
	
	/**
	 * ��ȡ��ǰ���
	 * @return int
	 */
	public int getChrrentYear(){
		return this.getYear(this.getCurrentDate());
	}
	
	/**
	 * ��ȡ��ǰ������
	 * @return int
	 */
	public int getCurrentMinute(){
		return this.getMinute(this.getCurrentDate());
	}
	
	/**
	 * ��ȡ��ǰ������
	 * @return long
	 */
	public long getCurrentMillis(){
		return this.getMillis(this.getCurrentDate());
	}
	
	/**
	 * ��ȡ��ǰСʱ��
	 * @return int
	 */
	public int getCurrentHour(){
		return this.getHour(this.getCurrentDate());
	}
	
	/**
	 * ��ȡ��ǰ����
	 * @return int
	 */
	public int getCurrentSecond(){
		return this.getSecond(this.getCurrentDate());
	}
	
	
}
