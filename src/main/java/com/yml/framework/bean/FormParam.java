package com.yml.framework.bean;

/**
 * 封装表单参数
 *
 * Created by Yuming-Liu
 * 日期： 2018-08-14
 * 时间： 19:48
 */
public class FormParam {

    private String filedName;

    private Object fieldValue;

    public FormParam(String filedName, Object fieldValue) {
        this.filedName = filedName;
        this.fieldValue = fieldValue;
    }

    public String getFiledName() {
        return filedName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
