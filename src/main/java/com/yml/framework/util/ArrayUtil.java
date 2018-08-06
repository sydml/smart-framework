package com.yml.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by Yuming-Liu
 * 日期： 2018-08-06
 * 时间： 23:31
 */
public final class ArrayUtil {
    /**
     * 判断数组是否非空
     */
    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    /**
     * 判断数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtil.isEmpty(array);
    }
}
