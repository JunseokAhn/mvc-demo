package com.example.mvcdemo.handler.apihandler;

import com.example.mvcdemo.handler.HandlerAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class ApiHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof ApiHandler;
    }

    @Override
    public String handle(HttpServletRequest req, HttpServletResponse res, Object objHandler) throws Exception {

        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(paramName -> {
            paramMap.put(paramName, req.getParameter(paramName));
        });
        ApiHandler handler = (ApiHandler) objHandler;

        //객체를 String으로 반환
        return new ObjectMapper().writeValueAsString(handler.process(paramMap));
    }
}
