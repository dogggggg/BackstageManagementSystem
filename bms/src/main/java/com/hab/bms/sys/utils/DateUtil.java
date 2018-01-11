package com.hab.bms.sys.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;

import com.hab.bms.sys.basicdata.tools.exceptions.UtilException;

public class DateUtil {
	/** 毫秒 */
	public final static long MS = 1;
	/** 每秒钟的毫秒数 */
	public final static long SECOND_MS = MS * 1000;
	/** 每分钟的毫秒数 */
	public final static long MINUTE_MS = SECOND_MS * 60;
	/** 每小时的毫秒数 */
	public final static long HOUR_MS = MINUTE_MS * 60;
	/** 每天的毫秒数 */
	public final static long DAY_MS = HOUR_MS * 24;

	/** 标准日期格式 */
	public final static String NORM_DATE_PATTERN = "yyyy-MM-dd";
	/** 标准时间格式 */
	public final static String NORM_TIME_PATTERN = "HH:mm:ss";
	/** 标准日期时间格式，精确到分 */
	public final static String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
	/** 标准日期时间格式，精确到秒 */
	public final static String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/** 标准日期时间格式，精确到毫秒 */
	public final static String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	/** HTTP头中日期时间格式 */
	public final static String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";

	/** 标准日期（不含时间）格式化器 */
	// private final static SimpleDateFormat NORM_DATE_FORMAT = new
	// SimpleDateFormat(NORM_DATE_PATTERN);
	private static ThreadLocal<SimpleDateFormat> NORM_DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(NORM_DATE_PATTERN);
		};
	};
	/** 标准时间格式化器 */
	// private final static SimpleDateFormat NORM_TIME_FORMAT = new
	// SimpleDateFormat(NORM_TIME_PATTERN);
	private static ThreadLocal<SimpleDateFormat> NORM_TIME_FORMAT = new ThreadLocal<SimpleDateFormat>() {
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(NORM_TIME_PATTERN);
		};
	};
	/** 标准日期时间格式化器 */
	// private final static SimpleDateFormat NORM_DATETIME_FORMAT = new
	// SimpleDateFormat(NORM_DATETIME_PATTERN);
	private static ThreadLocal<SimpleDateFormat> NORM_DATETIME_FORMAT = new ThreadLocal<SimpleDateFormat>() {
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(NORM_DATETIME_PATTERN);
		};
	};
	/** HTTP日期时间格式化器 */
	// private final static SimpleDateFormat HTTP_DATETIME_FORMAT = new
	// SimpleDateFormat(HTTP_DATETIME_PATTERN, Locale.US);
	private static ThreadLocal<SimpleDateFormat> HTTP_DATETIME_FORMAT = new ThreadLocal<SimpleDateFormat>() {
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(HTTP_DATETIME_PATTERN, Locale.US);
		};
	};

	/**
	 * 当前时间，格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 当前时间的标准形式字符串
	 */
	public static String now() {
		return formatDate(new Date());
	}

	/**
	 * 当前时间long
	 * 
	 * @param isNano
	 *            是否为高精度时间
	 * @return 时间
	 */
	public static long current(boolean isNano) {
		return isNano ? System.nanoTime() : System.currentTimeMillis();
	}

	/**
	 * 当前日期，格式 yyyy-MM-dd
	 * 
	 * @return 当前日期的标准形式字符串
	 */
	public static String today() {
		return formatDate(new Date());
	}

	/**
	 * @return 当前月份
	 */
	public static int thisMonth() {
		return month(date());
	}

	/**
	 * @return 今年
	 */
	public static int thisYear() {
		return year(date());
	}

	/**
	 * @return 当前时间
	 */
	public static Date date() {
		return new Date();
	}

	/**
	 * Long类型时间转为Date
	 * 
	 * @param date
	 *            Long类型Date（Unix时间戳）
	 * @return 时间对象
	 */
	public static Date date(long date) {
		return new Date(date);
	}

	/**
	 * 转换为Calendar对象
	 * 
	 * @param date
	 *            日期对象
	 * @return Calendar对象
	 */
	public static Calendar toCalendar(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * 获得月份
	 * 
	 * @param date
	 *            日期
	 * @return 月份
	 */
	public static int month(Date date) {
		return toCalendar(date).get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得年
	 * 
	 * @param date
	 *            日期
	 * @return 月份
	 */
	public static int year(Date date) {
		return toCalendar(date).get(Calendar.YEAR);
	}

	/**
	 * 获得年
	 * 
	 * @param date
	 *            日期
	 * @return 月份
	 */
	public static int season(Date date) {
		return toCalendar(date).get(Calendar.MONTH) / 3 + 1;
	}

	/**
	 * 获得指定日期年份和季节<br>
	 * 格式：[20171]表示2017年第一季度
	 * 
	 * @param date
	 *            日期
	 * @return Season ，类似于 20172
	 */
	public static String yearAndSeason(Date date) {
		return yearAndSeason(toCalendar(date));
	}

	/**
	 * 获得指定日期区间内的年份和季节<br>
	 * 
	 * @param startDate
	 *            其实日期（包含）
	 * @param endDate
	 *            结束日期（包含）
	 * @return Season列表 ，元素类似于 20172
	 */
	public static LinkedHashSet<String> yearAndSeasons(Date startDate, Date endDate) {
		final LinkedHashSet<String> seasons = new LinkedHashSet<String>();
		if (startDate == null || endDate == null) {
			return seasons;
		}

		final Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		while (true) {
			// 如果开始时间超出结束时间，让结束时间为开始时间，处理完后结束循环
			if (startDate.after(endDate)) {
				startDate = endDate;
			}

			seasons.add(yearAndSeason(cal));

			if (startDate.equals(endDate)) {
				break;
			}

			cal.add(Calendar.MONTH, 3);
			startDate = cal.getTime();
		}

		return seasons;
	}

	// ------------------------------------------------------------------------
	// Private method start
	/**
	 * 获得指定日期年份和季节<br>
	 * 格式：[20171]表示2017年第一季度
	 * 
	 * @param cal
	 *            日期
	 */
	private static String yearAndSeason(Calendar cal) {
		return new StringBuilder().append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH) / 3 + 1).toString();
	}
	// ------------------------------------------------------------------------
	// Private method end

	// ------------------------------------------------------------------------
	// Format start
	/**
	 * 根据特定格式格式化日期
	 * 
	 * @param date
	 *            被格式化的日期
	 * @param format
	 *            格式
	 * @return 格式化后的字符串
	 */
	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            被格式化的日期
	 * @return 格式化后的日期
	 */
	public static String formatDateTime(Date date) {
		return NORM_DATETIME_FORMAT.get().format(date);
	}

	/**
	 * 格式 yyyy-MM-dd
	 * 
	 * @param date
	 *            被格式化的日期
	 * @return 格式化后的字符串
	 */
	public static String formatDate(Date date) {
		return NORM_DATE_FORMAT.get().format(date);
	}

	/**
	 * 格式化为Http的标准日期格式
	 * 
	 * @param date
	 *            被格式化的日期
	 * @return HTTP标准形式日期字符串
	 */
	public static String formatHttpDate(Date date) {
		return HTTP_DATETIME_FORMAT.get().format(date);
	}
	// ------------------------------------------------------------------------
	// Format end

	// ------------------------------------------------------------------------
	// Parse start

	/**
	 * 构建Date对象
	 * 
	 * @param dateStr
	 *            Date字符串
	 * @param simpleDateFormat
	 *            格式化器
	 * @return Date对象
	 */
	public static Date parse(String dateStr, SimpleDateFormat simpleDateFormat) {
		try {
			return simpleDateFormat.parse(dateStr);
		} catch (Exception e) {
			throw new UtilException(
					StrUtil.format("Parse [{}] with format [{}] error!", dateStr, simpleDateFormat.toPattern()), e);
		}
	}

	/**
	 * 将特定格式的日期转换为Date对象
	 * 
	 * @param dateString
	 *            特定格式的日期
	 * @param format
	 *            格式，例如yyyy-MM-dd
	 * @return 日期对象
	 */
	public static Date parse(String dateString, String format) {
		return parse(dateString, new SimpleDateFormat(format));
	}

	/**
	 * 格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateString
	 *            标准形式的时间字符串
	 * @return 日期对象
	 */
	public static Date parseDateTime(String dateString) {
		return parse(dateString, NORM_DATETIME_FORMAT.get());
	}

	/**
	 * 格式yyyy-MM-dd
	 * 
	 * @param dateString
	 *            标准形式的日期字符串
	 * @return 日期对象
	 */
	public static Date parseDate(String dateString) {
		return parse(dateString, NORM_DATE_FORMAT.get());
	}

	/**
	 * 格式HH:mm:ss
	 * 
	 * @param timeString
	 *            标准形式的日期字符串
	 * @return 日期对象
	 */
	public static Date parseTime(String timeString) {
		return parse(timeString, NORM_TIME_FORMAT.get());
	}

	/**
	 * 格式：<br>
	 * 1、yyyy-MM-dd HH:mm:ss<br>
	 * 2、yyyy-MM-dd<br>
	 * 3、HH:mm:ss<br>
	 * 4、yyyy-MM-dd HH:mm 5、yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return 日期
	 */
	public static Date parse(String dateStr) {
		if (null == dateStr) {
			return null;
		}
		dateStr = dateStr.trim();
		int length = dateStr.length();
		try {
			if (length == NORM_DATETIME_PATTERN.length()) {
				return parseDateTime(dateStr);
			} else if (length == NORM_DATE_PATTERN.length()) {
				return parseDate(dateStr);
			} else if (length == NORM_TIME_PATTERN.length()) {
				return parseTime(dateStr);
			} else if (length == NORM_DATETIME_MINUTE_PATTERN.length()) {
				return parse(dateStr, NORM_DATETIME_MINUTE_PATTERN);
			} else if (length >= NORM_DATETIME_MS_PATTERN.length() - 2) {
				return parse(dateStr, NORM_DATETIME_MS_PATTERN);
			}
		} catch (Exception e) {
			throw new UtilException(StrUtil.format("Parse [{}] with format normal error!", dateStr));
		}

		// 没有更多匹配的时间格式
		throw new UtilException(StrUtil.format(" [{}] format is not fit for date pattern!", dateStr));
	}
	// ------------------------------------------------------------------------
	// Parse end

}
