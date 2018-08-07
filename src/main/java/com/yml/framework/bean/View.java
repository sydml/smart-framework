package com.yml.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuming-Liu
 * 日期： 2018-08-07
 * 时间： 22:19
 */
public class View {
    /**
     * 视图路径
     */
    private String path;

    /**
     * 模型数据
     */
    private Map<String,Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<String, Object>();
    }

    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
