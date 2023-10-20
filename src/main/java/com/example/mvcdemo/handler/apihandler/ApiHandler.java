package com.example.mvcdemo.handler.apihandler;

import com.example.mvcdemo.handler.Handler;

import java.util.Map;


public interface ApiHandler extends Handler {
    Object process(Map<String, String> paramMap) throws Exception;
}
