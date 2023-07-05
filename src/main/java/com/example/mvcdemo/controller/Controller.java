package com.example.mvcdemo.controller;

import com.example.mvcdemo.ui.Model;

import java.util.Map;

public interface Controller {
    String process(Model model, Map<String, String> paramMap) throws Exception;
}
