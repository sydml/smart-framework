package com.yml.framework.helper;

import com.yml.framework.annotation.Autowired;
import com.yml.framework.util.ArrayUtil;
import com.yml.framework.util.CollectionUtil;
import com.yml.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by Yuming-Liu
 * 日期： 2018-08-06
 * 时间： 23:14
 */
public final class IocHelper {
    static {
        //获取所有的Bean类与Bean实例之间的映射关系(Bean Map)
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntity : beanMap.entrySet()) {
                Class<?> beanClass = beanEntity.getKey();
                Object beanInstance = beanEntity.getValue();
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                        if (beanField.isAnnotationPresent(Autowired.class)) {
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                //通过反射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
