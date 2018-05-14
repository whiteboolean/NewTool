package com.mtool.toolslib.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gerry on 2017/5/2.
 */

public class TimeUtils {
    /**
     * @return ms转换ss
     */
    public static String getSeconds() {
        long time = new Date(System.currentTimeMillis()).getTime();
        int inttime = (int) (time / 1000);
        return inttime + "l";
    }

    public static String strToDateLong(String strDate) {
        try {
            switch (strDate.length()){
                case 14:
                    SimpleDateFormat inFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                    SimpleDateFormat toformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date parse = inFormat.parse(strDate);
                    String format = toformat.format(parse);
                    return format;
                case 12:
                    SimpleDateFormat inFormatA = new SimpleDateFormat("yyyyMMddHHmm");
                    SimpleDateFormat toformatA = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date parseA = inFormatA.parse(strDate);
                    String formatA = toformatA.format(parseA);
                    return formatA;
                default:
                    return "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatDate(String strDate) {
        return strDate.substring(0, 4) + "-" +
                strDate.substring(4, 6) + "-" +
                strDate.substring(6, 8);

    }

    public static String formatDateShort(String strDate) {
        return
                strDate.substring(4, 6) + "-" +
                        strDate.substring(6, 8);

    }

    public static String formatHour(String strDate) {
        return strDate.substring(8, 10) + "点" +
                strDate.substring(10, 12) + "分";

    }

    public static String formatTime(String strDate) {
        return formatDate(strDate) + " " + formatHour(strDate);

    }
}
