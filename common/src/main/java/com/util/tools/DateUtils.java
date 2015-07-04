package com.util.tools;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * <h3>日期工具类<h3>
 * <p>■ 获取当前日期（java.util.Date）</p>
 * <p>■ 获取当前时间戳（java.sql.TimeStamp）</p>
 * <p>■ 获取以天为单位的开始时间</p>
 * <p>■ 获取以天为单位的结束时间</p>
 * <p>■ 获取日期格式</p>
 * <p>■ 获取当前日期和时间（自定义格式）</p>
 * <p>■ 把日期型数据转换成字符串</p>
 * <p>■ 把字符串转换成日期型数据</p>
 * <p>■ 日期时间格式转换(如10/12/2012转化为2012-10-12)</p>
 * <p>■ 计算两个日期的间隔（yyyy MM dd HH mm ss）</p>
 * <p>■ 计算日期加上一段间隔之后的新日期（yyyy MM dd HH mm ss）</p>
 * <p>■ 计算年龄</p>
 * 
 * @author liangzh
 *
 * @since  common-0.0.1-SNAPSHOT
 * 
 * 2013年8月29日 下午3:13:15
 *
 */
public class DateUtils {
    public static final String FORMAT1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT2 = "yyyy-MM-dd";
    public static final String FORMAT3 = "MM/dd/yyyy HH:mm:ss";
    public static final String FORMAT4 = "MM/dd/yyyy";
    public static final String FORMAT5 = "HH:mm:ss";
    /** 年*/
    public static final int Y = 1;
    /**月*/
    public static final int M = 2;
    /**日*/
    public static final int D = 3;
    /**小时*/
    public static final int H = 4;
    /**分钟*/
    public static final int MI = 5;
    /**秒*/
    public static final int S = 6;

    /**
     * <b>获取当前日期（Long类型）</b>
     * 
     * @return Date
     */
    public static Long getCurrentLongDate() {
        return new Date().getTime();
    }

    /**
     * <b>获取当前日期（日期类型）</b>
     * 
     * @return Date
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * <b>获取当前时间戳</b>
     * 
     * @return Timestamp
     */
    public static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * <b>获取以天为单位的开始时间</b>
     * 
     * @param date  日期
     * 
     * @return Date
     */
    public static Date getStartTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * <b>获取以天为单位的结束时间</b>
     * 
     * @param date  日期
     * 
     * @return Date
     */
    public static Date getEndTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * <b>获取日期格式</b>
     * (支持的格式有：yyyy-MM-dd HH:mm:ss,yyyy-MM-dd HH:mm:ss.SSS（归到yyyy-MM-dd HH:mm:ss中,yyyy-MM-dd,MM/dd/yyyy HH:mm:ss,MM/dd/yyyy HH:mm:ss.SSS（归到MM/dd/yyyy HH:mm:ss中）,MM/dd/yyyy)
     * 
     * @param date 日期
     * 
     * @return String
     */
    public static String getFormat(String date) throws Exception {
        String reg1 = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}(.\\d{1,3}){0,1}";
        String reg2 = "\\d{4}-\\d{1,2}-\\d{1,2}";
        String reg3 = "\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}(.\\d{1,3}){0,1}";
        String reg4 = "\\d{1,2}/\\d{1,2}/\\d{4}";
        if (date.matches(reg1)) {
            return FORMAT1;
        } else if (date.matches(reg2)) {
            return FORMAT2;
        } else if (date.matches(reg3)) {
            return FORMAT3;
        } else if (date.matches(reg4)) {
            return FORMAT4;
        } else {
            throw new Exception("不支持的日期格式：" + date);
        }
    }

    /**
     * <b>获取当前日期和时间（自定义格式）</b>
     * (参考格式：yyyy年MM月dd日HH时（hh时）mm分ss秒ms毫秒E本月第F个星期,对应的值：2009年07月22日15时（03时）05分30秒530毫秒星期三本月第4个星期)
     * 
     * @param format 日期时间的格式
     * 
     * @return 当前日期和时间
     */
    public static String getCurrentDateTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(getCurrentDate());
    }

    /**
     * <b>把字符串转换成日期</b>
     * 
     * @param date String型日期
     * 
     * @return Date型日期
     */
    public static Date convertStringToDate(String date) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat(getFormat(date));
        return df.parse(date);
    }

    /**
     * <b>把日期类型转换成字符串</b>
     * 
     * @param date Date型日期
     * @param format 转换成String型日期后的格式
     * 
     * @return String型日期
     */
    public static String convertDateToString(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * <b>字符串类型日期格式转换</b>
     * 
     * @param value 转换前的值
     * @param format 转换后的格式
     * 
     * @return 转换后的值
     */
    public static String dateFormat(String value, String format) {
        try {
            Date date = convertStringToDate(value);
            return convertDateToString(date, format);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * <b>计算两个日期的间隔（yyyy MM dd HH mm ss）</b>
     * 
     * @param type 间隔的单位：1-年，2-月，3-日，4-小时，5-分钟，6-秒，不填默认为日
     * @param sdate1 String型日期
     * @param sdate2 String型日期
     * 
     * @return 间隔的数量。如果日期sdate2在日期sdate1之后，则结果为正数；如果日期sdate2在日期sdate1之前，则结果为负数
     */
    public static int dateDiff(int type, String sdate1, String sdate2) throws Exception {
        Date date1 = new SimpleDateFormat(getFormat(sdate1)).parse(sdate1);
        Date date2 = new SimpleDateFormat(getFormat(sdate2)).parse(sdate2);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
        if (type == Y) {//年
            return yearDiff;
        } else if (type == M) {//月
            int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
            return monthDiff;
        } else {
            long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);
            long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);
            if (type == H) {//小时
                return (int) ((ldate2 - ldate1) / (3600000));
            } else if (type == MI) {//分钟
                return (int) ((ldate2 - ldate1) / (60000));
            } else if (type == S) {//秒
                return (int) ((ldate2 - ldate1) / (1000));
            } else {//日
                return (int) ((ldate2 - ldate1) / (3600000 * 24));
            }
        }
    }

    /**
     * <b>计算日期加上一段间隔之后的新日期</b>
     * 
     * @param type 间隔的单位：1-年，2-月，3-日，4-小时，5-分钟，6-秒，不填默认为日
     * @param sdate String型日期
     * @param num 间隔数量
     * 
     * @return 返回新日期
     */
    public static String dateAdd(int type, String sdate, int num) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat(getFormat(sdate));
        Date date = df.parse(sdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (type == Y) {
            cal.add(Calendar.YEAR, num);
        } else if (type == M) {
            cal.add(Calendar.MONTH, num);
        } else if (type == H) {
            cal.add(Calendar.HOUR, num);
        } else if (type == MI) {
            cal.add(Calendar.MINUTE, num);
        } else if (type == S) {
            cal.add(Calendar.SECOND, num);
        } else {
            cal.add(Calendar.DATE, num);
        }
        return df.format(cal.getTime());
    }

    /**
     * <b>计算日期加上一段间隔之后的新日期</b>
     * 
     * @param type 间隔的单位：1-年，2-月，3-日，4-小时，5-分钟，6-秒，不填默认为日
     * @param timestamp String型日期
     * @param num 间隔数量
     * 
     * @return 返回新日期
     */
    public static Timestamp TimestampAdd(int type, Timestamp timestamp, int num) throws Exception {
        Date date = new Date(timestamp.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (type == Y) {
            cal.add(Calendar.YEAR, num);
        } else if (type == M) {
            cal.add(Calendar.MONTH, num);
        } else if (type == H) {
            cal.add(Calendar.HOUR, num);
        } else if (type == MI) {
            cal.add(Calendar.MINUTE, num);
        } else if (type == S) {
            cal.add(Calendar.SECOND, num);
        } else {
            cal.add(Calendar.DATE, num);
        }
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * <b>计算日期加上一段间隔之后的新日期</b>
     * 
     * @param type 间隔的单位：1-年，2-月，3-日，4-小时，5-分钟，6-秒，不填默认为日
     * @param date 日期
     * @param num 间隔数量
     * 
     * @return 返回新日期
     */
    public static Date DateAdd(int type, Date date, int num) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (type == Y) {
            cal.add(Calendar.YEAR, num);
        } else if (type == M) {
            cal.add(Calendar.MONTH, num);
        } else if (type == H) {
            cal.add(Calendar.HOUR, num);
        } else if (type == MI) {
            cal.add(Calendar.MINUTE, num);
        } else if (type == S) {
            cal.add(Calendar.SECOND, num);
        } else {
            cal.add(Calendar.DATE, num);
        }
        return cal.getTime();
    }

    /**
     * <b>计算年龄</b>
     * (1、对于天，两个日期直接相减 2、对于月，如果2011年3月26日出生，则2011年4月27日才满一个月 3、对于周岁，如果2010年4月26日出生，则2011年4月27日才满一周岁)
     * 
     * @param birthday	出生日期
     * @param type 年龄类型，Y-周岁，M-月，D-天，默认为周岁
     * 
     * @return 年龄数值
     * @throws Exception 
     */
    public static int calAge(String birthday, String type) throws Exception {
        String currDate = getCurrentDateTime("yyyy-MM-dd");
        if ("D".equalsIgnoreCase(type)) {
            return dateDiff(D, birthday, currDate);
        } else if ("M".equalsIgnoreCase(type)) {
            int result = dateDiff(M, birthday, currDate);
            String temp = dateAdd(M, birthday, result);
            if (dateDiff(D, temp, currDate) <= 0) {
                result--;
            }
            return result;
        } else {
            int result = dateDiff(Y, birthday, currDate);
            String temp = dateAdd(Y, birthday, result);
            if (dateDiff(D, temp, currDate) <= 0) {
                result--;
            }
            return result;
        }
    }

    /**
     * 获取上周日的时间
     * @author ljp
     * @return
     */
    public static Date getLastWeekSunday(){
        Calendar date=Calendar.getInstance(Locale.CHINA);
        date.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
        date.add(Calendar.WEEK_OF_MONTH,-1);//周数减一，即上周
        date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//日子设为星期天
        date.set(Calendar.HOUR_OF_DAY, 23);
        date.set(Calendar.MINUTE, 59);
        date.set(Calendar.SECOND, 59);
        date.set(Calendar.MILLISECOND,999);
        return date.getTime();
    }

    /**
     * <b>获取当前Long类型日期</b><br>
     * <b>dateStr:需要转换的日期格式字符串 例:"2000-01-01"</b><br>
     * <b>DateType:日期类型进行格式化：DateUtils.FORMAT[1~5]</b>
     *
     * @param dateStr
     * @param dateType
     * @return Long
     */
    public static Long getLongDate(String dateStr,String dateType) throws Exception {
        if (null == dateType)
            dateType = FORMAT1;
        SimpleDateFormat sdf = new SimpleDateFormat(dateType);
        Long times = sdf.parse(dateStr).getTime();

        return times;
    }

}
