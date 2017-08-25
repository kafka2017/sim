package com.amwell.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author hxj
 */
public final class DateHelper {

	public static final String FORMAT_DATE = "yyyy-MM-dd";

	public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

	/**
	 * @return 返回当前系统时间，格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowDateTimeStr() {
		return getDateTimeStr(new Date());
	}

	public static String getDateTimeStr(Date inputDate) {
		return new SimpleDateFormat(FORMAT_DATETIME).format(inputDate);
	}

	public static String getPassedMinute(int minutes) {
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.MINUTE, minutes);
		return getDateTimeStr(ca.getTime());
	}
	
	public static String getYesterDateStr(){
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_MONTH, -1);
		return getDateStr(ca.getTime());
	}
	
	private static String getDateStr(Date inputDate) {
		return new SimpleDateFormat(FORMAT_DATE).format(inputDate);
	}

	public static String getFormattedDateUtil(java.util.Date dtDate, String strFormatTo) {
		if (dtDate == null) {
			return "";
		}
		strFormatTo = strFormatTo.replace('/', '-');
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(strFormatTo);
			return formatter.format(dtDate);
		} catch (Exception e) {
			// Common.printLog("转换日期字符串格式时出错;" + e.getMessage());
			return "";
		}
	}

}
