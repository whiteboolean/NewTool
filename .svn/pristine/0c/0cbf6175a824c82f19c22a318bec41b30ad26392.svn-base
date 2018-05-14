package com.mtool.toolslib.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

public class DateUtil {
	/**
	 * 取得當時的日期及時間，並在開頭加上一個大寫字母D，時間格式為：yyyyMMdd_HHmmssSSS<br />
	 * Ex：西元2011年6月5日下午3點18分42秒087毫秒 --> D20110605_151842087<br />
	 *
	 * @return 日期及時間字串，精準度到毫秒
	 */
	public static synchronized String getDateTime() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
		Date date = new Date();
		String strDate = "D" + sdFormat.format(date);
		return strDate;
	}

	/**
	 * 取得當時的日期及時間，時間格式為：yyyy-MM-ddHHmmss<br />
	 * Ex：西元2011年6月5日下午3點18分42秒 --> 2011-06-05151842<br />
	 *
	 * @return 日期及時間字串，精準度到秒
	 */
	public static synchronized String getNewDateTime() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-ddHHmmss");
		Date date = new Date();
		return sdFormat.format(date);
	}

	/**
	 * 取得傳入日期物件所代表的日期及時間，並在開頭加上一個大寫字母D，時間格式為：yyyyMMdd_HHmmssSSS<br>
	 * 最後三位的單位為毫秒，是隨機產生的數字
	 *
	 * @param date
	 *            日期物件
	 * @return 日期及時間字串，精準度到毫秒
	 */
	public static synchronized String getDateTime(Date date) {
		if (date == null) {
			return getDateTime();
		}

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String strDate = "D" + sdFormat.format(date);
		try {
			Thread.sleep(500);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			strDate += random.nextInt(10);
		}
		return strDate;
	}

	/**
	 * 取得當時時間(HH:mm)
	 *
	 * @return 24小時制(0-23)時間字串
	 */
	public static String getTime() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		String time = sdFormat.format(date);
		return time;
	}

	/**
	 * 將民國年日期(100/11/12)轉換成西元年日期(2011-11-12)，包含分隔符號的轉換
	 *
	 * @param cDate
	 *            民國年日期
	 * @return 西元年日期
	 */
	public static String getEDate(String cDate) {
		System.out.println("cdate == " + cDate);
		String strDate = cDate.replaceAll("/", "-");
		String date = strDate;
		int index = strDate.indexOf("-");
		if (index > -1) {
			// System.out.println("strDate == " + strDate);
			// System.out.println("index == " + index);
			// System.out.println("str == " + strDate.substring(0, index));
			int eyear = Integer.parseInt(strDate.substring(0, index));
			if (eyear < 1911) {
				eyear += 1911;
			}
			date = Integer.toString(eyear) + strDate.substring(index);
		}
		return date;
	}

	/*
	 * 格式 2000-02-02 转乘 02月 02日
	 */
	public static String getMonthAndDate(String EDate) {
		if (EDate != null) {
			String Month = EDate.substring(5, 7);
			String Date = EDate.substring(8, 10);
			return Month + "月" + Date + "日";
		} else {
			return "";
		}

	}

	/**
	 * 取得傳入日期的西元年格式
	 *
	 * @param date
	 *            日期
	 * @return 格式化後的日期(yyyy-MM-dd)
	 */
	public static String getEDate(Date date) {
		if (date == null) {
			return getEDate();
		}

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdFormat.format(date);
		return strDate;
	}

	/**
	 * 取得當天西元年日期，ex：2011-12-31
	 *
	 * @return 西元年日期
	 */
	public static String getEDate() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		return strDate;
	}

	/**
	 * 將西元年日期(2011-12-31)轉換成民國年日期(100/12/31)
	 *
	 * @param eDate
	 *            西元年日期(格式：yyyy-MM-dd)
	 * @return 民國年日期(格式：###/MM/dd)
	 */
	public static String getCDate(String eDate) {
		if (eDate != null) {
			int index = eDate.indexOf("-");
			String date = eDate;
			if (index > -1) {
				int cyear = Integer.parseInt(eDate.substring(0, index)) - 1911;
				if (cyear < 1) {
					cyear = cyear - 1;
				}
				String yyy = String.format("%03d", cyear);
				date = yyy + eDate.substring(index);
			}
			date = date.replaceAll("-", "/");
			return date;
		} else {
			return "";
		}
	}

	public static String getCDate(Date date) {
		return getCDate(getEDate(date));
	}

	/**
	 * 取得當天民國年日期，ex：100/10/10
	 *
	 * @return 當天日期(民國年格式)
	 */
	public static String getCDate() {
		return getCDate(getEDate());
	}

	/**
	 * 計算民國年(YYY/MM/dd)的日期位移 offset 個天數後的日期，回傳格式也是民國年格式。<br />
	 * 例如：
	 * <ol>
	 * <li>DateUtility.getOtherDate(100/10/18,
	 * 20)，則可得到民國100年10月18日向後推算20日的日期：100年11月7日</li>
	 * <li>DateUtility.getOtherDate(100/10/18,
	 * -4)，則可得到民國100年10月18日向前推算4日的日期：100年10月14日</li>
	 * </ol>
	 *
	 * @param date
	 *            日期(民國年格式：YYY/MM/dd)
	 * @param offset
	 *            位移天數：大於 0 表向後推算；小於 0 表向前推算
	 * @return 推算出來的日期(民國年格式：YYY/MM/dd)
	 */
	public static String getOtherDate(String date, int offset) {
		String[] elements = date.split("/");
		int year = Integer.parseInt(elements[0]) + 1911;
		int month = Integer.parseInt(elements[1]);
		int day = Integer.parseInt(elements[2]);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		calendar.add(Calendar.DAY_OF_YEAR, offset);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdFormat.format(calendar.getTime());
		return getCDate(strDate);
	}

	/**
	 * 計算西元年(yyyy-MM-dd)的日期位移 offset 個天數後的日期，回傳格式也是西元年格式。<br />
	 * 例如：
	 * <ol>
	 * <li>DateUtility.getEOtherDate(2011-10-18,
	 * 20)，則可得到2011年10月18日向後推算20日的日期2011年11月7日</li>
	 * <li>DateUtility.getEOtherDate(2011-10-18,
	 * -4)，則可得到2011年10月18日向前推算4日的日期2011年10月14日</li>
	 * </ol>
	 *
	 * @param date
	 *            日期(西元年格式：yyyy-MM-dd)
	 * @param offset
	 *            位移天數：大於 0 表向後推算；小於 0 表向前推算
	 * @return 推算出來的日期(西元年格式：yyyy-MM-dd)
	 */
	public static String getEOtherDate(String date, int offset) {
		String[] elements = date.split("-");
		int year = Integer.parseInt(elements[0]);
		int month = Integer.parseInt(elements[1]);
		int day = Integer.parseInt(elements[2]);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		calendar.add(Calendar.DAY_OF_YEAR, offset);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdFormat.format(calendar.getTime());
		return strDate;
	}

	/**
	 * 將民國年日期(年/月/日)做驗證，回傳是否為正確日期
	 *
	 * @param date
	 *            民國年
	 * @return 合法日期回傳true，否則回傳false
	 */
	public static boolean validateCDate(String date) {
		int year = 0;
		int month = 0;
		int day = 0;
		boolean result = false;

		int index1 = date.indexOf("/");
		if (index1 > -1) {
			year = Integer.parseInt(date.substring(0, index1));
			int index2 = date.substring(index1 + 1).indexOf("/");
			if (index2 > -1) {
				month = Integer.parseInt(date.substring(index1 + 1, index1 + index2 + 1));
				day = Integer.parseInt(date.substring(index1 + index2 + 2));
				result = validateDate(year + 1911, month, day);
			}
		}
		return result;
	}

	/**
	 * 將西元年日期(年-月-日)做驗證，回傳是否為正確日期
	 *
	 * @param date
	 *            民國年
	 * @return 合法日期回傳true，否則回傳false
	 */
	public static boolean validateEDate(String date) {
		int year = 0;
		int month = 0;
		int day = 0;
		boolean result = false;

		int index1 = date.indexOf("-");
		if (index1 > -1) {
			year = Integer.parseInt(date.substring(0, index1));
			int index2 = date.substring(index1 + 1).indexOf("-");
			if (index2 > -1) {
				month = Integer.parseInt(date.substring(index1 + 1, index1 + index2 + 1));
				day = Integer.parseInt(date.substring(index1 + index2 + 2));
				result = validateDate(year, month, day);
			}
		}
		return result;
	}

	/**
	 * 驗證日期是否正確(大月、小月、閏月)
	 *
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return 日期符合大、小、閏月的天數傳回true，否則傳回false
	 */
	public static boolean validateDate(int year, int month, int day) {
		boolean result = false;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if (day > 0 && day <= 31) {
				result = true;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			if (day > 0 && day <= 30) {
				result = true;
			}
			break;
		case 2:
			if (year % 4 == 0 && (year % 100 > 0 || year % 400 == 0) && day > 0 && day <= 29) {
				result = true;
			} else if (day > 0 && day <= 28) {
				result = true;
			}
			break;
		default:
			break;
		}
		return result;
	}

	/**
	 * 驗證時間是否正確()
	 *
	 *
	 * @param hours
	 *            小時
	 * @param minute
	 *            分鐘
	 * @return 日期符合大、小、閏月的天數傳回true，否則傳回false
	 */
	public static boolean validateTime(int hours, int minute) {
		boolean result = false;
		if (hours >= 0 && hours <= 24) {
			if (minute >= 0 && minute <= 60) {
				result = true;
			} else {
				System.out.print("時間輸入錯誤");
			}
		} else {
			System.out.print("時間輸入錯誤");
		}
		return result;
	}

	/**
	 * 取得該月份最後一天的天數
	 *
	 * @param year
	 *            西元年
	 * @param month
	 *            月
	 * @return 當月份的天數
	 */
	public static String getLastDate(String year, String month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
		return String.valueOf(cal.getActualMaximum(Calendar.DATE));
	}

	/**
	 * 取得 該日期加月 之後的年月日
	 *
	 * @param strdate
	 *            欲計算的日期
	 * @param months
	 *            月
	 * @return String 加完後的日期
	 */
	public static String AddMonth(String strdate, int months) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdFormat.parse(strdate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, months);// 日期+ months
			strdate = sdFormat.format(calendar.getTime());
			strdate = strdate.substring(0, 8) + "01";
			return strdate;
		} catch (ParseException ex) {
			Log.e("AddMonth", "日期加月數發生錯誤" + ex.getMessage());
			return strdate;
		}
	}

	/**
	 * 取得 該日期加上幾年 之後的年月日
	 *
	 * @param strdate
	 *            欲計算的日期
	 * @param year
	 *            月
	 * @return String 加完後的日期
	 */
	public static String AddYear(String strdate, int year) {

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdFormat.parse(strdate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, year);// 日期+ months
			strdate = sdFormat.format(calendar.getTime());
		} catch (ParseException ex) {
			Log.e("AddYear", "日期轉換錯誤" + ex.getMessage());
		}

		return strdate;

	}

	/**
	 * 日期的比較大小
	 *
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return String equal,before,after equal=日期相同。日期1 小於 日期2 before。日期1 大於 日期2
	 *         after
	 */
	public static String compareDate(String date1, String date2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = df.parse(date1);
			Date d2 = df.parse(date2);
			if (d1.equals(d2)) {
				return "equals";
			} else if (d1.before(d2)) {
				return "before";
			} else {
				return "after";
			}
		} catch (ParseException ex) {
			Log.e("compareDate", "日期比較轉換錯誤" + ex.getMessage());
			return "null";
		}
	}

	/**
	 * 
	 * @param t1
	 * @param t2
	 * @return -1 T2 > T1 1 T2 < T1
	 */
	public static int TimeCompare(String t1, String t2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(formatter.parse(t1));
			c2.setTime(formatter.parse(t2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int result = c1.compareTo(c2);
		return result;
	}

	/**
	 * 將民國年的日期時分秒改成西元年的日期時分秒
	 *
	 * @param cDate
	 *            民國年日期時分秒
	 * @return String 西元年日期時分秒
	 */
	public static String getEDateTime(String cDate) {
		String strDate;
		if (cDate.contains("/")) {
			strDate = cDate.replaceAll("/", "-");
		} else {
			strDate = cDate.substring(0, 3) + "-" + cDate.substring(3, 5) + "-" + cDate.substring(5, 7)
					+ cDate.substring(7);
		}
		String date = strDate;
		int index = strDate.indexOf("-");
		if (index > -1) {
			int eyear = Integer.parseInt(strDate.substring(0, index));
			if (eyear < 1911) {
				eyear += 1911;
			}
			date = Integer.toString(eyear) + strDate.substring(index);
		}
		date = date.substring(0, 10) + " " + date.substring(10, 12) + ":" + date.substring(12, 14) + ":"
				+ date.substring(14);
		return date;
	}

	/**
	 * 取得二個日期時分秒的差
	 *
	 * @param EDateS
	 *            起始西元年-月-日 時:分:秒
	 * @param EDateE
	 *            結束西元年-月-日 時:分:秒
	 * @return long 二個日期的相差秒數
	 */
	public static Long getSeconds(String EDateS, String EDateE) {
		Long result = (long) 0;
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化時間
		try {
			result = (d.parse(EDateS).getTime() - d.parse(EDateE).getTime()) / 1000;// 当前时间减去测试时间
																					// 这个的除以1000得到秒，相应的60000得到分，3600000得到小时
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int lastDayOfMonth(int year, int month) {
		int result = 31;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			result = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			result = 30;
			break;
		case 2:
			result = (year % 4 == 0) ? 29 : 28;
			break;
		}
		return result;
	}

	public static String Age(String birthday, String regDate) {
		int yy = Integer.parseInt(birthday.substring(0, 4));
		int mm = Integer.parseInt(birthday.substring(5, 7));
		int dd = Integer.parseInt(birthday.substring(8));

		int ryy = Integer.parseInt(regDate.substring(0, 4));
		int rmm = Integer.parseInt(regDate.substring(5, 7));
		int rdd = Integer.parseInt(regDate.substring(8));

		Calendar birth = new GregorianCalendar(yy, mm, dd);
		Calendar regDt = new GregorianCalendar(ryy, rmm, rdd);
		int day = regDt.get(Calendar.DAY_OF_MONTH) - birth.get(Calendar.DAY_OF_MONTH);
		int month = regDt.get(Calendar.MONTH) - birth.get(Calendar.MONTH);
		int year = regDt.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
		// 按照减法原理，先day相减，不够向month借；然后month相减，不够向year借；最后year相减。
		if (day < 0) {
			month -= 1;
			regDt.add(Calendar.MONTH, -1);// 得到上一个月，用来得到上个月的天数。
			day = day + regDt.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		if (month < 0) {
			month = (month + 12) % 12;
			year--;
		}
		return year + "/" + month + "/" + day;

	}

	public static String getFormattedDate(String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date date = Calendar.getInstance().getTime();
		return dateFormat.format(date);
	}

	public static String getDayOfWeek(String date) {
		if (date == null) {
			return "";
		}
		String[] dateAr = new String[3];
		dateAr = date.split("-");
		int year = Integer.parseInt(dateAr[0]);
		int month = Integer.parseInt(dateAr[1]);
		int day = Integer.parseInt(dateAr[2]);
		Calendar cal = Calendar.getInstance();
		month = month - 1;
		cal.set(year, month, day);

		// 取得星期幾的整數值
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		String dayofweek = "";
		// 判斷取得的數值等於星期幾
		switch (dayOfWeek) {
		case Calendar.SUNDAY:
			dayofweek = "日";
			break;
		case Calendar.MONDAY:
			dayofweek = "一";
			break;
		case Calendar.TUESDAY:
			dayofweek = "二";
			break;
		case Calendar.WEDNESDAY:
			dayofweek = "三";
			break;
		case Calendar.THURSDAY:
			dayofweek = "四";
			break;
		case Calendar.FRIDAY:
			dayofweek = "五";
			break;
		case Calendar.SATURDAY:
			dayofweek = "六";
		}
		return dayofweek;
	}

	public static int getSetion(String time) {
		String[] ts = time.split(":");
		int hour = Integer.parseInt(ts[0]);
		if (hour < 12) {
			return 1;
		} else if (hour >= 12 && hour < 18) {
			return 2;
		} else if (hour >= 18) {
			return 3;
		} else {
			return 0;
		}

	}

	/**
	 * 將西元年日期(2015-12-31)轉換年日期(2015年12月31日)
	 *
	 * @param eDate
	 *            西元年日期(格式：yyyy-MM-dd)
	 * @return 民國年日期(格式：###/MM/dd)
	 */
	public static String DateFormatToYear(String eDate) {
		String reformattedStr = null;
		SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat myFormat = new SimpleDateFormat("MM月dd");

		try {

			reformattedStr = myFormat.format(fromUser.parse(eDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reformattedStr;
	}

	/**
	 * 将月份转为中文显示 例如：
	 * 
	 */
	public static String MonthFormateToChinese(String month) {
		String ChineseMonth[] = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };
		Log.e("MonthTOChinese", "" + Integer.valueOf(month));
		return ChineseMonth[Integer.valueOf(month) - 1];
	}

	public static long getCurrentMills() {
		return System.currentTimeMillis();
	}

	public static long getCurrentSeconds() {
		return System.currentTimeMillis() / 1000;
	}


	/**
	 * 获取当月的 天数
	 */
	public static String getCurrentMonthDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return String.valueOf(maxDate);
	}

	public static String getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		int i = cal.get(Calendar.MONTH) + 1;
		String s = i < 10 ? "0" : "";
		return s + i;
	}

	public static String getCurrentDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		int i = cal.get(Calendar.DAY_OF_MONTH);
		String s = i < 10 ? "0" : "";
		return s + i;
	}

	public static String getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return String.valueOf(cal.get(Calendar.YEAR));
	}

	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return String.valueOf(cal.get(Calendar.DATE));
	}


	/**
	 * 根据年 月 获取对应的月份 天数
	 */
	public static int getDaysByYearMonth(int year, int month) {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 根据日期 找到对应日期的 星期
	 */
	public static String getDayOfWeekByDate(String date) {
		String dayOfweek = "-1";
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = myFormatter.parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat("E");
			dayOfweek = formatter.format(myDate);

		} catch (Exception e) {
			System.out.println("错误!");
		}
		return dayOfweek;
	}

	public static int getIndexOfWeekByDate(String date) {
		String weekDate = getDayOfWeekByDate(date);
		String[] week = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
		ArrayList array = new ArrayList<>(Arrays.asList(week));

		return array.indexOf(weekDate);
	}

	public static Date changeToDateWithSD(String str,SimpleDateFormat sdm) {
		try {
			return sdm.parse(str);
		} catch (Exception ex) {
			return new Date();
		}
	}
	public static Date changeToDate(String str) {
		try {
			return SDM1.parse(str);
		} catch (Exception ex) {
			return new Date();
		}
	}

	public static final SimpleDateFormat SDM1 = new SimpleDateFormat("yyyyMMddHHmm", Locale.CHINA);
	public static final SimpleDateFormat SDM2 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
	public static final SimpleDateFormat SDM3 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
	public static final SimpleDateFormat SDM4 = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
	public static final SimpleDateFormat SDM5 = new SimpleDateFormat("yyyy年MM月", Locale.CHINA);
}
