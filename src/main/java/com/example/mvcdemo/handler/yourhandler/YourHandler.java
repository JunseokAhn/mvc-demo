package com.example.mvcdemo.handler.yourhandler;

import com.example.mvcdemo.ui.Model;

import java.util.Map;

public interface YourHandler {
    String process(Model model, Map<String, String> paramMap) throws Exception;
}
