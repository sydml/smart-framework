package com.yml.framework;

import com.yml.framework.annotation.Controller;
import com.yml.framework.helper.BeanHelper;
import com.yml.framework.helper.ClassHelper;
import com.yml.framework.helper.IocHelper;
import com.yml.framework.util.ClassUtil;

/**
 * Created by Yuming-Liu
 * 日期： 2018-08-07
 * 时间： 00:03
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                Controller.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(),false);
        }
    }
}
