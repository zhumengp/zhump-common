package com.zhump.dataCommon;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期帮助类
 */
public class DateUtil {
	/**
	 * 获取当前时间
	 */
	public static Timestamp getNowDate() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 获取今天开始时间戳
	 * 
	 * @return
	 */
	public static Timestamp getTodayStartTimeStamp() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 001);
		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * 获取当前时间 时分秒都是0
	 */
	public static Timestamp getNowDateNoHMS() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * yyyy-MM-dd 转换成 Date
	 */
	public static Date getYYYYMMDD(String date) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.parse(date);
	}

	/**
	 * Date 转换成 yyyy-MM-dd
	 */
	public static String getYYYYMMDD(Date date) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	/**
	 * Date 转换成 YYYYMMDDHHMMSS
	 */
	public static Long getYYYYMMDDHHMMSS(Date date) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return Long.parseLong(dateFormat.format(date));
	}

	/**
	 * Date 转换成 YYYY-MM-DD HH-mm-ss 24小时制的时间
	 */
	public static String getyyyyMMdd24HHmmss(Date date) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	/**
	 * Date 转换成 YYYY-MM-DD HH-mm-ss 12小时制的时间
	 */
	public static String getyyyyMMdd12hhmmss(Date date) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return dateFormat.format(date);
	}

	/**
	 * 获取相差的每一天日期
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Timestamp> getDifferDate(Date startDate, Date endDate) {
		if (startDate.getTime() - endDate.getTime() > 0) {
			return null;
		}
		List<Timestamp> list = new ArrayList<Timestamp>();
		if (startDate.getTime() - endDate.getTime() == 0) {
			list.add(new Timestamp(startDate.getTime()));
			return list;
		}
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(startDate);
		calendar2.setTime(endDate);

		while (calendar1.getTime().getTime() != calendar2.getTime().getTime()) {
			list.add(new Timestamp(calendar1.getTime().getTime()));
			calendar1.set(Calendar.DAY_OF_MONTH, (calendar1.get(Calendar.DAY_OF_MONTH) + 1));
		}
		list.add(new Timestamp(calendar2.getTime().getTime()));
		return list;
	}

	/**
	 * 根据日期字符串和类型返回时间(如传入 "2014-11-1",true返回 "2014-11-1 00:00:00"
	 * 传入"2014-11-4",false返回 "2014-11-4 23:59:59")
	 * 
	 * @param dateStr
	 *            (格式:2014-11-1)
	 * @param Boolean
	 * 
	 */
	public static String convertDateStr(String dateStr, Boolean convertType) {
		String newDateStr = "";
		try {
			if (convertType) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date day = format.parse(dateStr);
				SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				newDateStr = format2.format(day);
			} else {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date day = format.parse(dateStr);
				long curMillisecond = day.getTime();// 当天的毫妙
				int dayMis = 1000 * 60 * 60 * 24;
				long resultMis = curMillisecond + (dayMis - 1); // 当天最后一秒
				SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date resultDate = new Date(resultMis);
				newDateStr = format2.format(resultDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDateStr;
	}

	/**
	 * 判断两个日期是否是同一天
	 * 
	 * @param date1
	 *            date1
	 * @param date2
	 *            date2
	 * @return
	 */
	public static boolean isSameDate(Timestamp t1, Timestamp t2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(t1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(t2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}

	/**
	 * 获取当前日期
	 */
	public static String getCurDateHms() {
		Date date = new Date();// 当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化对象
		return sdf.format(date);
	}

	/**
	 * 获取当前日期
	 */
	public static String getCurDate() {
		Date date = new Date();// 当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化对象
		return sdf.format(date);
	}

	/**
	 * 当前时间是否在一个时间段内
	 * 
	 * @param date
	 * @param strDateBegin
	 * @param strDateEnd
	 * @return
	 * @throws ParseException 
	 */
	public static boolean isInDate(Date date, String strDateBegin, String strDateEnd) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(strDateBegin.equals(strDateEnd)&&strDateBegin.equals(sdf.format(new Date()))){
			return true;
		}
		Date dateBegin = sdf.parse(strDateBegin);
		Date dateEnd =sdf.parse(strDateEnd);
		return date.compareTo(dateBegin)>=0&&date.compareTo(dateEnd)<0;
	}
	
	/**
	 * 获取过去的天数
	 */
	public static String getDate(Integer day) {
		Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - day);  
        Date today = calendar.getTime();  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        String result = format.format(today);  
        return result;  
	}
}
