package com.example.mvcdemo.handler.pagehandler;

import com.example.mvcdemo.handler.Handler;
import com.example.mvcdemo.ui.Model;

import java.util.Map;

public interface PageHandler extends Handler {
    Object process(Model model, Map<String, String> paramMap) throws Exception;
}
