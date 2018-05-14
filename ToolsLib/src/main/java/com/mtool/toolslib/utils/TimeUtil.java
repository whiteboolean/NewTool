package com.mtool.toolslib.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by JayCruz on 2017/11/30.
 */

public class TimeUtil {

    public static String[] hourarray = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
    public static String[] minutearray = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"
            , "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};


    /**
     * @return ms转换ss
     */
    public static String getSeconds() {
        long time = new Date(System.currentTimeMillis()).getTime();
        int inttime = (int) (time / 1000);
        return inttime + "l";
    }

    public static String encryption(String code) {
        if (code.isEmpty() || "null".equals(code)) {
            return null;
        }
        if (code.length() > 9) {
            String start = code.substring(0, 4);
            String end = code.substring(code.length() - 4, code.length());
            return start + "**************" + end;
        } else {
            return code;
        }


    }

    public static List<String> getYearMonthDayHourMinuteSecond() {
        List<String> list = new ArrayList<>();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int x = 0; x <= 30; x++) {
            Calendar c = Calendar.getInstance();
            int day = c.get(Calendar.DAY_OF_MONTH) - x;
            c.set(Calendar.DAY_OF_MONTH, day);
            Date time = c.getTime();
            String d = format.format(time);
            list.add(d);
        }

        return list;
    }

    public static String getYearStr() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, day);
        Date time = c.getTime();
        String year = format.format(time);
        return year;
    }


    public static String judgeString(String str) {
        int i = str.indexOf("");
        String substring = str;
        if (i != -1) {
            substring = str.substring(0, i);
        }

        return substring;
    }

    public static Date getSystemDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return curDate;
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }


    /**
     * 获取当前的年份，整形
     */
    public static int getYearInt() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前的年份，整形
     */
    public static int getMonthInt() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        return calendar.get(Calendar.MONTH)+1;
    }


    /**
     * 获取当前的年份，整形
     */
    public static int getDateInt() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        return calendar.get(Calendar.DATE);
    }
}
