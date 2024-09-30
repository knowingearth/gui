package com.y.gui.common.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DateUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getDateTimeStr(Date date) {
        if (null == date) {
            return null;
        }
        return dateTimeFormat.format(date);
    }

    public static Date getDateTime(String date) {
        if (!StringUtils.hasText(date)) {
            return null;
        }
        try {
            return dateTimeFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getDate(String date) {
        if (!StringUtils.hasText(date)) {
            return null;
        }
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getDateStr(Date date) {
        if (null == date) {
            return null;
        }
        return dateFormat.format(date);
    }

    public static Date getDayTimeEnd(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
}
