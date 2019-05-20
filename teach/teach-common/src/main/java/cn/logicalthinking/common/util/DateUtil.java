package cn.logicalthinking.common.util;

import cn.logicalthinking.common.exception.BusinessException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 获取不同格式的时间
 *
 * @author 黄世杰
 * @version 1.0
 * @Description
 * @2018-5-23
 */
public class DateUtil {
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE = "yyyy-MM-dd";
    public static final String YEAR_MONTH = "yyyy-MM";
    public static final String YMDHM = "yyyy-MM-dd HH:mm";
    public static final String DATE1 = "yyyyMMdd";
    public static final String TIME = "HH:mm:ss";
    public static final String TimeStamp = "yyyyMMddHHmmssSSS";

    public static final String DATETWO = "yyyy-MM-dd HH";

//    public static void main(String[] args) throws Exception {
//        //System.out.println(getDateStr(DateUtil.addDate(new Date(),1),DATE_TIME));
////        System.out.println(getSpecifiedSomeDaysAgo(new Date(), -1, DateUtil.DATE));
//        System.out.println(compare_dateTree("2018-11-07 09:45:56", DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME)));
//    }

    /**
     * 转换时间格式
     *
     * @param date   时间
     * @param format 时间格式
     * @return
     */
    public static String getDateStr(Date date, String format) {
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 转换时间格式
     *
     * @param date   时间
     * @param format 时间格式
     * @return
     */
    public static Date getDateParse(String date, String format) {
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.parse(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 传入一个开始时间和结束时间   返回这个时间段数组
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @throws ParseException
     */
    public static String[] getArrTime(String startTime, String endTime) throws ParseException {
        List<String> list = new ArrayList<String>();
        SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd");
        list.add(startTime);
        int day = daysBetween(startTime, endTime);//，计算出开始时间和结束时间相隔几天
        for (int i = 0; i < day; i++) {
            Date data1 = sb.parse(startTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(data1);
            cal.add(Calendar.DATE, 1);
            startTime = sb.format(cal.getTime());
            list.add(startTime);
        }
        return list.toArray(new String[1]);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param startTime 较小的时间
     * @param endTime   较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(String startTime, String endTime) throws ParseException {
        SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd");
        Date smdate = sb.parse(startTime);
        Date bdate = sb.parse(endTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * 计算两个日期之间相差的天数
     *
     * @param startTime 较小的时间
     * @param endTime   较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(String startTime, String endTime, String patternt) throws ParseException {
        SimpleDateFormat sb = new SimpleDateFormat(patternt);
        Date smdate = sb.parse(startTime);
        Date bdate = sb.parse(endTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 以某个时间加上多少天后得到的新日期
     *
     * @param date
     * @param day
     * @return
     * @throws ParseException
     */
    public static Date addDate(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }

    /**
     * 以某个时间加上多少分后得到的新日期
     *
     * @param date
     * @param day
     * @return
     * @throws ParseException
     */
    public static Date addDateTimes(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }

    /**
     * 以某个时间加上多少分后得到的新日期
     *
     * @param date
     * @param day
     * @return
     * @throws ParseException
     */
    public static Date addDateTime(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }

    /**
     * 计算两个时间的间隔，例如计算 2016 年 1 月 1 日距离现在有多少天。
     *
     * @param data 时间类型的字符串
     * @return 返回的是该日期  和当前日期隔的多少天   返回的是天数
     * @throws ParseException
     */
    public static long timeDifference(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME);
        Calendar calendar = Calendar.getInstance();
        long nowDate = calendar.getTime().getTime(); //Date.getTime() 获得毫秒型日期
        long specialDate = sdf.parse(data).getTime();
        long betweenDate = (specialDate - nowDate) / (1000 * 60 * 60 * 24); //计算间隔多少天，则除以毫秒到天的转换公式
        return betweenDate;
    }

    /**
     * 根据某个特定的时间 date 计算某个时间
     *
     * @param date1
     * @param time  根据年计算1    根据月计算 2    根据一个星期计算 3    根据一天计算 5     根据小时计算 10
     * @param year  以某个时间后的当前年
     * @return
     * @throws ParseException
     */
    public static String getAffterYear(String date1, int time, int year) throws ParseException {
        SimpleDateFormat sb = new SimpleDateFormat(DATE_TIME);
        Calendar specialDate = Calendar.getInstance();
        specialDate.setTime(sb.parse(date1)); //注意在此处将 specialDate 的值改为特定日期
        //Calendar.YEAR   根据年计算    1
        //Calendar.MONTH   根据月计算  2
        //Calendar.WEEK_OF_YEAR   根据一个星期计算  3
        //Calendar.DATE   根据一天计算 5
        //Calendar.HOUR   根据小时计算 10
        specialDate.add(time, year); //特定时间的后
        Date date = specialDate.getTime();
        return sb.format(date);
    }

    /**
     * 传入一个时间字符串的数组    返回该数组中最大的时间值
     *
     * @param arr String[] arr={"2011-20-15","2011-20-14","2011-20-20","2011-20-18","2011-20-19"};
     * @return 返回该数组中最大的时间值
     * @throws ParseException
     */
    public static String getMax1(String[] arr) throws ParseException {
        SimpleDateFormat sb = new SimpleDateFormat(DATE);
        Date[] as = new Date[arr.length];
        for (int i = 0; i < arr.length; i++) {
            Date str = compare_dateOne(arr[i]);
            as[i] = str;
        }
        for (int i = 0; i < as.length - 1; i++) {
            for (int j = 0; j < as.length - i - 1; j++) {
                if (as[j].getTime() < as[j + 1].getTime()) {
                    Date aa = as[j];
                    as[j] = as[j + 1];
                    as[j + 1] = aa;
                }
            }
        }
        return sb.format(as[0]);
    }

    /**
     * 传入一个时间字符串  返回一个date类型
     *
     * @param DATE1
     * @return
     * @throws ParseException
     */
    private static Date compare_dateOne(String DATE1) throws ParseException {
        SimpleDateFormat sb = new SimpleDateFormat(DATE);
        Date date = sb.parse(DATE1);
        return date;
    }

    /**
     * 方式一
     * 两个日期字符进行比较   返回大的日期字符串  相等返回=   注意  只能比较到分
     *
     * @param DATE1 日期一
     * @param DATE2 日期二
     * @param type  日期格式     如  yyyy-MM-dd HH:mm   yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String compare_dateOne(String DATE1, String DATE2, String type) throws ParseException {
        SimpleDateFormat sb = new SimpleDateFormat(type);
        Date date = sb.parse(DATE1);
        Date date1 = sb.parse(DATE2);
        if (date.after(date1)) {
            return DATE1;
        }
        if (date.before(date1)) {
            return DATE2;
        } else {
            return "=";
        }
    }

    /**
     * 方式二
     * 两个日期字符进行比较   返回大的日期字符串  相等返回=   注意  只能比较到分
     *
     * @param DATE1 日期一
     * @param DATE2 日期二
     * @param type  日期格式     如  yyyy-MM-dd HH:mm   yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String compare_date(String DATE1, String DATE2, String type) throws ParseException {
        DateFormat df = new SimpleDateFormat(type);
        Date dt1 = df.parse(DATE1);
        Date dt2 = df.parse(DATE2);
        if (dt1.getTime() > dt2.getTime()) {
            return DATE1;
        } else if (dt1.getTime() < dt2.getTime()) {
            return DATE2;
        } else {
            return "=";
        }
    }

    public static boolean compare_time(String time1, String time2) {
        try {
            DateFormat df = new SimpleDateFormat("HH:mm:ss");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
            Date dt1 = df.parse(time1);//将字符串转换为date类型
            Date dt2 = df.parse(time2);
            if (dt1.getTime() > dt2.getTime())//比较时间大小,如果dt1大于dt2
                return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 方式三
     * 两个日期字符进行比较   返回大的日期字符串  相等返回=   注意  能精确到秒的比较
     *
     * @param DATE1 日期一
     * @param DATE2 日期二
     * @return
     * @throws Exception
     */
    public static int compare_dateTree(String DATE1, String DATE2) throws Exception {
        String date1 = DATE1.trim();

        String date2 = DATE2.trim();
        date1 = DATE1.replace("-", "")
                .replace("-", "")
                .replace(":", "")
                .replace(" ", "")
                .replace(":", "");
        date2 = DATE2.replace("-", "")
                .replace("-", "")
                .replace(" ", "")
                .replace(":", "")
                .replace(":", "");
        long time1 = 0;
        long time2 = 0;
        try {
            time1 = Long.parseLong(date1);
            time2 = Long.parseLong(date2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new Exception("转换异常,请检查日期格式是否正确");
        }
        if (time1 > time2) {
            return 1;
        } else if (time1 < time2) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 返回两个字符串日期的相差天数
     *
     * @param DATE1 日期一
     * @param DATE2 日期二
     * @return 返回相差的天数
     * @throws ParseException
     */
    public static int daysBetWeen(String DATE1, String DATE2) throws ParseException {
        SimpleDateFormat sb = new SimpleDateFormat(DATETWO);
        Calendar cc = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;
        long daysBetWeen = 0;
        cc.setTime(sb.parse(DATE1));
        time1 = cc.getTimeInMillis();
        cc.setTime(sb.parse(DATE2));
        time2 = cc.getTimeInMillis();
        daysBetWeen = (time1 - time2) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(daysBetWeen));
    }

    /**
     * 返回两个字符串日期的相差多少个小时
     *
     * @param DATE1 日期一
     * @param DATE2 日期二
     * @return 返回相差的小时
     * @throws ParseException
     */
    public static int daysBetWeen2(String DATE1, String DATE2) throws ParseException {
        SimpleDateFormat sb = new SimpleDateFormat(DATETWO);
        Calendar cc = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;
        long daysBetWeen = 0;
        cc.setTime(sb.parse(DATE1));
        time1 = cc.getTimeInMillis();
        cc.setTime(sb.parse(DATE2));
        time2 = cc.getTimeInMillis();
        daysBetWeen = (time1 - time2) / (1000 * 3600);
        return Integer.parseInt(String.valueOf(daysBetWeen));
    }

    /**
     * 由过去的某一时间,计算距离当前的时间
     */
    public static String CalculateTime(String time) {
        long nowTime = System.currentTimeMillis(); // 获取当前时间的毫秒数
        String msg = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 指定时间格式
        Date setTime = null; // 指定时间
        try {
            setTime = sdf.parse(time); // 将字符串转换为指定的时间格式
        } catch (ParseException e) {

            e.printStackTrace();
        }

        long reset = setTime.getTime(); // 获取指定时间的毫秒数
        long dateDiff = nowTime - reset;

        if (dateDiff < 0) {
            msg = "输入的时间不对";
        } else {

            long dateTemp1 = dateDiff / 1000; // 秒
            long dateTemp2 = dateTemp1 / 60; // 分钟
            long dateTemp3 = dateTemp2 / 60; // 小时
            long dateTemp4 = dateTemp3 / 24; // 天数
            long dateTemp5 = dateTemp4 / 30; // 月数
            long dateTemp6 = dateTemp5 / 12; // 年数

            if (dateTemp6 > 0) {
                msg = dateTemp6 + "年前";

            } else if (dateTemp5 > 0) {
                msg = dateTemp5 + "个月前";

            } else if (dateTemp4 > 0) {
                msg = dateTemp4 + "天前";

            } else if (dateTemp3 > 0) {
                msg = dateTemp3 + "小时前";

            } else if (dateTemp2 > 0) {
                msg = dateTemp2 + "分钟前";

            } else if (dateTemp1 > 0) {
                msg = "刚刚";

            }
        }
        return msg;

    }

    /**
     * 获得该月最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份  
        cal.set(Calendar.YEAR, year);
        //设置月份  
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数  
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数  
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    /**
     * 传入年份   返回该年每月的第一天和最后一天的集合
     *
     * @param year
     * @return
     */
    public static List<String> getMonth(String year) {
        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= 12; i++) {
            String str = "";
            String lastDay = DateUtil.getLastDayOfMonth(Integer.parseInt(year), i);
            if (i < 10) {
                str = year + "-" + "0" + i + "-" + "01" + "/" + lastDay;
            } else {
                str = year + "-" + i + "-" + "01" + "/" + lastDay;
            }
            list.add(str);
        }
        return list;
    }

    /**
     * 获取当前时间 yyyy-MM-dd HH:mm:ss格式
     *
     * @return
     */
    public static String getSimpleCurrentDate() {
        return getDateStr(new Date(), DATE_TIME);
    }


    /**
     * 获取七天前的日期
     *
     * @return
     * @throws ParseException
     */
    public static String getSevenDaysAgo() throws ParseException {
        return getSevenDaysAgo(DateUtil.DATE);

    }

    /**
     * 获取七天前的日期
     *
     * @param pattern 日期样式，如yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String getSevenDaysAgo(String pattern) throws ParseException {
        return getSomeDaysAgo(-7, pattern);

    }

    /**
     * 获取some天前的日期
     *
     * @param some    负数，之前；正数，之后；0，当天
     * @param pattern 日期样式，如yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String getSomeDaysAgo(int some, String pattern) throws ParseException {

        return getSpecifiedSomeDaysAgo(null, some, pattern);

    }

    /**
     * 获取指定日期some天前的日期
     *
     * @param date    指定日期
     * @param some    负数，之前；正数，之后；0，当天;
     * @param pattern 日期样式，如yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String getSpecifiedSomeDaysAgo(Date date, int some, String pattern) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.add(Calendar.DATE, some);

        Date monday = c.getTime();

        String preMonday = sdf.format(monday);

        return preMonday;

    }

    public static String getStartDayOfMonth(int year, int month, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        Date strDateFrom = calendar.getTime();
        return DateUtil.getDateStr(strDateFrom, pattern);
    }

    public static String getStartDayOfMonth(int year, int month) {
        return getStartDayOfMonth(year, month, DateUtil.DATE);
    }

    public static String getEndDayOfMonth(int year, int month) {
        return getEndDayOfMonth(year, month, DateUtil.DATE);
    }

    public static String getEndDayOfMonth(int year, int month, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date strDateFrom = calendar.getTime();
        return DateUtil.getDateStr(strDateFrom, pattern);
    }

    public static void main(String[] args) {

        //得到月末
//        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//        Date strDateTo = calendar.getTime();
//        System.out.println(strDateTo.toLocaleString());
    }
}
