package com.yml.framework.bean;

import com.yml.framework.util.CastUtil;

import java.util.Map;

/**
 * Created by Yuming-Liu
 * 日期： 2018-08-07
 * 时间： 00:08
 */
public class Param {

    private Map<String,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取long型的参数值
     */
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * 或取所有字段信息
     */
    public Map<String, Object> getMap() {
        return paramMap;
    }
}
