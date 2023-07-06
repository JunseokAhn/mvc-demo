package com.example.mvcdemo.handler.myhandler;

import com.example.mvcdemo.ui.Model;

import java.util.Map;

public interface MyHandler {
    String process(Model model, Map<String, String> paramMap) throws Exception;
}
