package com.zcwl.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static final int SECOND = 1000;

	private static final int MIMUTE = SECOND * 60;

	private static final int HOUR = MIMUTE * 60;

	private static final int DAY = HOUR * 24;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 将毫秒数换算成x天x时x分x秒x毫秒
	 * 
	 * @param ms
	 * @return
	 */
	public static String formatMs2String(long ms) {

		long day = ms / DAY;
		long hour = (ms - day * DAY) / HOUR;
		long minute = (ms - day * DAY - hour * HOUR) / MIMUTE;
		long second = (ms - day * DAY - hour * HOUR - minute * MIMUTE) / SECOND;
		long milliSecond = ms - day * DAY - hour * HOUR - minute * MIMUTE
				- second * SECOND;

		StringBuffer sb = new StringBuffer();

		if (day > 0) {
			sb.append(day).append("天");
			if (hour > 0) {
				sb.append(hour).append("小时");
			}
			if (minute > 0) {
				sb.append(minute).append("分钟");
			}

			if (second > 0) {
				sb.append(second).append("秒");
			}
			return sb.toString();
		}

		if (hour > 0) {
			sb.append(hour).append("小时");
			if (minute > 0) {
				sb.append(minute).append("分钟");
			}

			if (second > 0) {
				sb.append(second).append("秒");
			}
			return sb.toString();
		}

		if (minute > 0) {
			sb.append(minute).append("分钟");
			if (second > 0) {
				sb.append(second).append("秒");
			}
			return sb.toString();
		}

		if (second > 0) {
			sb.append(second).append("秒");
			return sb.toString();
		}

		sb.append(milliSecond).append("毫秒");
		return sb.toString();

	}
	
	/**
	 * 将时间戳转为标准时间
	 * @param timestamp
	 * @return
	 */
	public static String formatLong2String(long timestamp){
		Date date = new Date(timestamp);
		return sdf.format(date);
	}
	
	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public static String formatDate2String(Date date){
		return dateSdf.format(date);
	}
	
	public static String formatDate2String2(Date date){
		return sdf.format(date);
	}
	
	public static void main(String[] agrs) {
		System.out.println(formatMs2String(61100 * 60));
	}

}
