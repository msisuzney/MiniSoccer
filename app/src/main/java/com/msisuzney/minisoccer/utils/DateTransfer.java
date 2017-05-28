package com.msisuzney.minisoccer.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by chenxin.
 * Date: 2017/5/25.
 * Time: 14:45.
 */

public class DateTransfer {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
    private static final TimeZone srcTimeZone = TimeZone.getTimeZone("GMT");
    private static final TimeZone destTimeZone = TimeZone.getTimeZone("GMT+8");

    private static final SimpleDateFormat format_Y = new SimpleDateFormat("yyyy");
    private static final SimpleDateFormat format_M = new SimpleDateFormat("MM");
    private static final SimpleDateFormat format_D = new SimpleDateFormat("dd");
    private static final SimpleDateFormat format_H = new SimpleDateFormat("HH");
    private static final SimpleDateFormat format_m = new SimpleDateFormat("mm");
    private static final SimpleDateFormat birthdayDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static Date now = new Date();

    private static String this_Year = format_Y.format(now);
    private static String this_M = format_M.format(now);
    private static String this_D = format_D.format(now);
    private static String this_H = format_H.format(now);
    private static String this_m = format_m.format(now);
    private static int nowDay = Integer.valueOf(this_D);
    private static int nowHour = Integer.valueOf(this_H);

    /**
     * 将标准时间转换成北京时间，yyyy-MM-dd HH:mm:ss
     *
     * @param sourceDate 标准时间
     * @return 北京时间
     * @throws ParseException
     */
    public static String transfer(String sourceDate) throws ParseException {
        Date srcDate = formatter.parse(sourceDate);
        Long targetTime = srcDate.getTime() - srcTimeZone.getRawOffset() + destTimeZone.getRawOffset();
        return formatter.format(targetTime).trim();
    }

    /**
     * 将出生日期转换成年龄
     *
     * @param birthday 出生日期
     * @return 年龄
     * @throws ParseException
     */
    public static int getAgeByBirthday(String birthday) throws ParseException {
        int age = 0;
        Date birthdayDate = birthdayDateFormat.parse(birthday);

        String birth_Y = format_Y.format(birthdayDate);
        String birth_M = format_M.format(birthdayDate);
        String birth_D = format_D.format(birthdayDate);

        age = Integer.valueOf(this_Year) - Integer.valueOf(birth_Y);
        if (this_M.compareTo(birth_M) <= 0 && this_D.compareTo(birth_D) < 0) {
            age--;
        }
        if (age < 0) {
            age = 0;
        }
        return age;

    }

    /**
     * 计算同一个月中
     *
     * @param date
     * @throws ParseException
     */
    public static int getHoursAgo(String date) throws ParseException {
        Date publishDate = formatter.parse(date);
        int publishDay = Integer.valueOf(format_D.format(publishDate));
        int publishHour = Integer.valueOf(format_H.format(publishDate));

        return (nowDay - publishDay) * 24 + nowHour - publishHour;
    }
}
