package com.yml.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Yuming-Liu
 * 日期： 2018-07-31
 * 时间： 00:02
 */
public final class StringUtil {
    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String string) {
        if (string != null) {
            string = string.trim();
        }
        return StringUtils.isEmpty(string);
    }

    /**
     * 判断是否为非空
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }
}
