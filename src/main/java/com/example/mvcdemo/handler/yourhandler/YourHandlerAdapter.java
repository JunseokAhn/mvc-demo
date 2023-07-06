package com.example.mvcdemo.handler.yourhandler;

import com.example.mvcdemo.handler.HandlerAdapter;
import com.example.mvcdemo.handler.myhandler.MyHandler;
import com.example.mvcdemo.ui.Model;
import com.example.mvcdemo.ui.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class YourHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof YourHandler;
    }

    @Override
    public ModelAndView handle(HttpServletRequest req, HttpServletResponse res, Object objHandler) throws Exception {

        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(paramName -> {
            paramMap.put(paramName, req.getParameter(paramName));
        });
        Model model = new Model();
        MyHandler handler = (MyHandler) objHandler;
        String viewName = handler.process(model, paramMap);
        ModelAndView modelAndView = new ModelAndView(viewName, model);
        return modelAndView;
    }
}
