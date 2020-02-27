package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

    //通过正则表达式判断是否为yyyy-mm-dd格式的日期
    public static boolean isDate(String str) {
        String el = "\\d{4}-\\d{2}-\\d{2}";
        String e2 = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
        Pattern p = Pattern.compile(el);
        Pattern p2 = Pattern.compile(e2);
        Matcher m = p.matcher(str);
        Matcher m2 = p2.matcher(str);
        boolean dateFlag = m.matches() || m2.matches();
        if (!dateFlag) {
            return false;
        }
        return true;
    }

    public static String getMonthFirstDay(String year, String month) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMinimum(Calendar.DAY_OF_MONTH));
        return df.format(calendar.getTime());
    }


    public static String getMonthFirstDay(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMinimum(Calendar.DAY_OF_MONTH));
        return df.format(calendar.getTime());
    }

    public static String getMonthLastDay(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        return df.format(calendar.getTime());
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendar.MONTH);
    }

    /**
     * 得到本月的最后一天
     *
     * @return
     */
    public static String getMonthLastDay(String year, String month) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        return df.format(calendar.getTime());
    }

    public static String getTodayDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    public static String getToDayString() {

        String str = UUID.randomUUID().toString();
        str = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return str.substring(0, 12);
    }

    public static String getToDayTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    public static String getTodayTimeNos() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    public static String getToDayTimeSS() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    public static String getToDayTimeSSS() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    public static String getToWeekAgoDayTimeSS() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, i - 6);
        return df.format(calendar.getTime());
    }

    public static String getToDayTimeCn() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    public static String getDayTimeCn(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return df.format(calendar.getTime());
    }

    public static String getWeekEndBefore(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.DAY_OF_WEEK == 1) {
            return df.format(calendar.getTime());
        } else {
            for (int i = 0; i < 7; i++) {
                calendar.add(Calendar.DATE, -1);
                if (calendar.get(calendar.DAY_OF_WEEK) == 1) {
                    break;
                }
            }
            return df.format(calendar.getTime());
        }
    }

    public static Map dateGrow(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + n);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Map map = new HashMap();
        map.put("day", cal.get(cal.DAY_OF_MONTH));
        String week = "";
        switch (cal.get(cal.DAY_OF_WEEK) - 1) {
            case 0:
                week = "日";
                break;
            case 1:
                week = "一";
                break;
            case 2:
                week = "二";
                break;
            case 3:
                week = "三";
                break;
            case 4:
                week = "四";
                break;
            case 5:
                week = "五";
                break;
            case 6:
                week = "六";
                break;
        }
        map.put("week", week);
        map.put("date", df.format(cal.getTime()));
        return map;
    }


    public static Map dateGrow(String dateString, int n) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + n);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Map map = new HashMap();
        map.put("day", cal.get(cal.DAY_OF_MONTH));
        String week = "";
        switch (cal.get(cal.DAY_OF_WEEK) - 1) {
            case 0:
                week = "日";
                break;
            case 1:
                week = "一";
                break;
            case 2:
                week = "二";
                break;
            case 3:
                week = "三";
                break;
            case 4:
                week = "四";
                break;
            case 5:
                week = "五";
                break;
            case 6:
                week = "六";
                break;
        }
        map.put("week", week);
        map.put("date", df.format(cal.getTime()));
        return map;
    }


    public static Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateString);
        return date;
    }

    public static String getWeek(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(dateString);
            String[] weeks = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (week_index < 0) {
                week_index = 0;
            }
            return weeks[week_index];
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getWeekCount(String year, String month) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.parseInt(year)); // 2010年
        c.set(Calendar.MONTH, Integer.parseInt(month) - 1); // 6 月
        return c.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    public static String getWeekendDate(String begin, String end) throws ParseException {
        Calendar cal = Calendar.getInstance();
        String weekendDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = sdf.parse(begin);
        Date endDate = sdf.parse(end);
        int days = (int) ((endDate.getTime() - beginDate.getTime()) / 1000 / 60 / 60 / 24);
        for (int i = 0; i <= days; i++) {
            cal.setTime(beginDate);
            cal.add(Calendar.DATE, i);
            if (cal.get(cal.DAY_OF_WEEK) == 1) {
                weekendDate = sdf.format(cal.getTime());
            }
        }
        return weekendDate;
    }


    public static String isNull(Object o) {
        if (o != null) {
            return o.toString();
        } else {
            return "";
        }
    }

    public static int isNullNum(Object o) {
        if (o != null) {
            return Integer.parseInt(o.toString());
        } else {
            return 0;
        }
    }

    /**
     * 获取时间字符串的毫秒数
     *
     * @param dateTime 必须为 yyyy-MM-dd HH:mm:ss 格式
     * @return 如果传入空值则返回当前时间
     */
    public static long getTimeInMillis(String dateTime) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (dateTime == null || "".equals(dateTime)) {
                c.setTime(new Date());
            } else {
                c.setTime(sf.parse(dateTime));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.getTimeInMillis();
    }

} 
