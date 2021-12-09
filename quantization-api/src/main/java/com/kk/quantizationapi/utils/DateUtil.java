package com.kk.quantizationapi.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: kk
 * @Date: 2021/11/18 17:49
 */
public class DateUtil {

    public static final String PATTERN_STANDARD08W = "yyyyMMdd";
    public static final String PATTERN_STANDARD12W = "yyyyMMddHHmm";
    public static final String PATTERN_STANDARD14W = "yyyyMMddHHmmss";
    public static final String PATTERN_STANDARD17W = "yyyyMMddHHmmssSSS";

    public static final String PATTERN_STANDARD10H = "yyyy-MM-dd";
    public static final String PATTERN_STANDARD16H = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_STANDARD19H = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_STANDARD10X = "yyyy/MM/dd";
    public static final String PATTERN_STANDARD16X = "yyyy/MM/dd HH:mm";
    public static final String PATTERN_STANDARD19X = "yyyy/MM/dd HH:mm:ss";
    //public static final String PATTERN_WEB_FRONT = "[0-9]{4}[/][0-9]{1,2}[/][0-9]{1,2}[ ][0-9]{1,2}[:][0-9]{1,2}";
    public static final String PATTERN_WEB_FRONT = "[0-9]{4}[/][0-9]{1,2}[/][0-9]{1,2}";


    /**
     *  修改字符串中的日期格式，兼容 yyyy/MM/dd
     *  @param strx 带日期的字符串
     *  @return String 替换后的字符串
     *
     */
    public static String UniteFormat(String strx)
    {
        Pattern pattern = Pattern.compile(PATTERN_WEB_FRONT);
        Matcher matcher = pattern.matcher(strx);

        String dateStr = null;
        while(matcher.find()){
            dateStr = matcher.group(0);
            String str =dateStr.toString();
            strx = strx.replace(str, str.replace("/", "-"));
        }
        return strx;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt 日期
     * @return String  当前日期是星期几
     */
    public static String getWeekOfDateChr(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt 日期
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }


    /**
     *  date2String
     * 日期格式的时间转化成字符串格式的时间
     * @author YFB
     * @param date 日期
     * @param pattern 模式
     * @return String 日期字符串
     */
    public static String date2String(Date date, String pattern) {
        if (date == null) {
            throw new java.lang.IllegalArgumentException("timestamp null illegal");
        }
        pattern = (pattern == null || pattern.equals(""))?PATTERN_STANDARD19H:pattern;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     *  string2Date
     * 字符串格式的时间转化成日期格式的时间
     * @author YFB
     * @param strDate 日期字符串
     * @param pattern 模式
     * @return Date 日期
     */
    public static Date string2Date(String strDate, String pattern) {
        if (strDate == null || strDate.equals("")) {
            throw new RuntimeException("strDate is null");
        }
        pattern = (pattern == null || pattern.equals(""))?PATTERN_STANDARD19H:pattern;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     *  getCurrentTime
     * 取得当前系统时间
     * @author YFB
     * @param format 格式 17位(yyyyMMddHHmmssSSS) (14位:yyyyMMddHHmmss) (12位:yyyyMMddHHmm) (8位:yyyyMMdd)
     * @return String 当前时间字符串
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        String date = formatDate.format(new Date());
        return date;
    }

    /**
     *  getWantDate
     * 获取想要的时间格式
     * @author YFB
     * @param dateStr 字符串
     * @param wantFormat 格式
     * @return String 日期字符串
     */
    public static String getWantDate(String dateStr,String wantFormat){
        if(!"".equals(dateStr)&&dateStr!=null){
            String pattern = PATTERN_STANDARD14W;
            int len = dateStr.length();
            switch(len){
                case 8:pattern = PATTERN_STANDARD08W;break;
                case 12:pattern = PATTERN_STANDARD12W;break;
                case 14:pattern = PATTERN_STANDARD14W;break;
                case 17:pattern = PATTERN_STANDARD17W;break;
                case 10:pattern = (dateStr.contains("-"))?PATTERN_STANDARD10H:PATTERN_STANDARD10X;break;
                case 16:pattern = (dateStr.contains("-"))?PATTERN_STANDARD16H:PATTERN_STANDARD16X;break;
                case 19:pattern = (dateStr.contains("-"))?PATTERN_STANDARD19H:PATTERN_STANDARD19X;break;
                default:pattern = PATTERN_STANDARD14W;break;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(wantFormat);
            try {
                SimpleDateFormat sdfStr = new SimpleDateFormat(pattern);
                Date date = sdfStr.parse(dateStr);
                dateStr = sdf.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dateStr;
    }

    /**
     *  getAfterTime
     * 获取该时间的几分钟之后的时间
     * @author YFB
     * @param dateStr 日期字符串
     * @param minute 分钟数
     * @return String 日期字符串
     */
    public static String getAfterTime(String dateStr,int minute){
        String returnStr = "";
        try {
            String pattern = PATTERN_STANDARD14W;
            int len = dateStr.length();
            switch(len){
                case 8:pattern = PATTERN_STANDARD08W;break;
                case 10:pattern = PATTERN_STANDARD10H;break;
                case 12:pattern = PATTERN_STANDARD12W;break;
                case 14:pattern = PATTERN_STANDARD14W;break;
                case 16:pattern = PATTERN_STANDARD16H;break;
                case 17:pattern = PATTERN_STANDARD17W;break;
                case 19:pattern = PATTERN_STANDARD19H;break;
                default:pattern = PATTERN_STANDARD14W;break;
            }
            SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
            Date date = null;
            date = formatDate.parse(dateStr);
            Date afterDate = new Date(date.getTime()+(60000*minute));
            returnStr = formatDate.format(afterDate);
        } catch (Exception e) {
            returnStr = dateStr;
            e.printStackTrace();
        }
        return returnStr;
    }
    public static String formatDate(long millisecond, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date(millisecond);
        return dateFormat.format(date);
    }

    /**
     * getBeforeTime
     * 获取该时间的几分钟之前的时间
     * @author YFB
     * @param dateStr 参数
     * @param minute 参数
     * @return String 返回
     */
    public static String getBeforeTime(String dateStr,int minute){
        String returnStr = "";
        try {
            String pattern = PATTERN_STANDARD14W;
            int len = dateStr.length();
            switch(len){
                case 8:pattern = PATTERN_STANDARD08W;break;
                case 10:pattern = PATTERN_STANDARD10H;break;
                case 12:pattern = PATTERN_STANDARD12W;break;
                case 14:pattern = PATTERN_STANDARD14W;break;
                case 16:pattern = PATTERN_STANDARD16H;break;
                case 17:pattern = PATTERN_STANDARD17W;break;
                case 19:pattern = PATTERN_STANDARD19H;break;
                default:pattern = PATTERN_STANDARD14W;break;
            }
            SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
            Date date = null;
            date = formatDate.parse(dateStr);
            Date afterDate = new Date(date.getTime()-(60000*minute));
            returnStr = formatDate.format(afterDate);
        } catch (Exception e) {
            returnStr = dateStr;
            e.printStackTrace();
        }
        return returnStr;
    }


    /**
     * dateAdd
     * 按照传入的field增加或减少对应的日期字段和对应的数值
     * @param date 需要被增加的日期
     * @param field Calendar.DATE等字段
     * @param amount 增加或减少的数量，增加时为正数，减少时为负数
     * @return 修改后的日期
     */
    public static Date addDate(Date date, int field, int amount){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, amount);
        return c.getTime();
    }
    /**
     * 日期格式校验
     * @param dateStr 字符日期
     * @param dateFormat 日期格式
     * @return 返回校验结果 true false
     */
    public static boolean isValidDate(String dateStr, String dateFormat) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(dateStr);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }

        return convertSuccess;
    }

    public static void main(String[] args){
        //System.out.println(DateUtil.getWantDate("2011-01-01 23:59:23", "yyyyMMdd"));
        System.out.println(DateUtil.formatDate(-999999999999L, "yyyyMMdd"));
    }
}
