package com.example.mvcdemo.handler;

import com.example.mvcdemo.ui.Model;

import java.util.Map;

public interface Handler {
    default Object process(Map<String, String> paramMap) throws Exception {
        return null;
    }

    default Object process(Model model, Map<String, String> paramMap) throws Exception {
        return null;
    }
}
