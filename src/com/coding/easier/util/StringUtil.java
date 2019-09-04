package com.coding.easier.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;

import static java.util.regex.Pattern.*;

/**
 * @author: D丶Cheng
 * @description: 字符串工具类
 * @create: 2019-08-29 11:43
 **/
public class StringUtil {

    private final static char UNDERSCORE = '_';

    /**
     * 字符串转换成常量
     *
     * @param s
     * @return
     */
    public static String toConstant(String s) {
        if (StringUtils.isBlank(s)) {
            return "";
        }
        s = s.replaceAll("\\s+", "_");
        StringBuilder stringBuilder = new StringBuilder();
        char previousChar = ' ';
        char c;
        for (int i = 0, len = s.length(); i < len; i++) {
            c = s.charAt(i);
            if (i != 0 && i + 1 < s.length() && Character.isUpperCase(c) && Character.isLowerCase(s.charAt(i + 1))) {
                stringBuilder.append("_").append(c);
            } else if (Character.isLowerCase(previousChar) && Character.isUpperCase(c)) {
                stringBuilder.append("_").append(c);
            } else {
                stringBuilder.append(c);
            }
            previousChar = c;
        }
        return stringBuilder.toString().toUpperCase();
    }

    /**
     * 驼峰格式字符串转换为下划线格式字符串
     *
     * @param s
     * @return
     */
    public static String camelToUnderscore(String s) {
        if (StringUtils.isBlank(s)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        char c;
        for (int i = 0, len = s.length(); i < len; i++) {
            c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                stringBuilder.append(UNDERSCORE);
                stringBuilder.append(Character.toLowerCase(c));
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式
     *
     * @param str
     * @return
     */
    public static String underscoreToCamel(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        Matcher mc = compile("_").matcher(str);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            stringBuilder.replace(position - 1, position + 1, stringBuilder.substring(position, position + 1).toUpperCase());
        }
        return stringBuilder.toString();
    }

    public static String blankToUnderscoreCase(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        char c;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, len = str.length(); i < len; i++) {
            c = str.charAt(i);
            if (c == ' ') {
                stringBuilder.append("_");
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    public static String blankToCamelCase(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        char c;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, len = str.length(); i < len; i++) {
            c = str.charAt(i);
            if (c == ' ') {
                stringBuilder.append((char) (str.charAt(++i) - 32));
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }
}
