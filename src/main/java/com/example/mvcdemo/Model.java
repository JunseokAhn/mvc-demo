package com.example.mvcdemo;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Component
public class Model {

    private Map<String, String> modelMap = new HashMap<>();
    public Map<String,String> getModelMap(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                modelMap.put(field.getName(), value != null ? value.toString() : null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return modelMap;
    }
}
