package com.example.mvcdemo.ui;

import lombok.*;

import java.util.Map;

@Data
public class Model {
    private String name;
    private Map<String, Object> valueMap;

    public void setValueMap(String str, Object obj) {
        this.valueMap.put(str, obj);
    }
}
