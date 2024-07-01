package com.y.gui.common.utils;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {
    private static final DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前日期字符串
     */
    public static String getNowDate() {
        return LocalDateTime.now().format(dateFmt);
    }

    /**
     * 根据指定格式获取当前日期字符串
     */
    public static String getNowDate(String pattern) {
        if (!org.springframework.util.StringUtils.hasText(pattern)) {
            return getNowDate();
        }
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 字符串转日期
     */
    public static LocalDate getLocalDate(String date, String pattern) {
        if (!org.springframework.util.StringUtils.hasText(date) || !org.springframework.util.StringUtils.hasText(pattern)) {
            return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间字符串
     */
    public static String getNowDateTime() {
        return LocalDateTime.now().format(dateTimeFmt);
    }

    /**
     * 根据指定格式获取当前时间字符串
     */
    public static String getNowDateTime(String pattern) {
        if (!org.springframework.util.StringUtils.hasText(pattern)) {
            return getNowDateTime();
        }
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间-无毫秒
     */
    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now().withNano(0);
    }

    /**
     * 字符串转时间
     */
    public static LocalDateTime getLocalDateTime(String date, String pattern) {
        if (!org.springframework.util.StringUtils.hasText(date) || !StringUtils.hasText(pattern)) {
            return null;
        }
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static void main(String[] args) {
        System.out.println(getNowDate("MMdd"));
    }
}
