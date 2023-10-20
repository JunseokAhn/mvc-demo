package com.example.mvcdemo.handler.pagehandler;

import com.example.mvcdemo.handler.HandlerAdapter;
import com.example.mvcdemo.ui.Model;
import com.example.mvcdemo.ui.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class PageHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof PageHandler;
    }

    @Override
    public ModelAndView handle(HttpServletRequest req, HttpServletResponse res, Object objHandler) throws Exception {

        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(paramName -> {
            paramMap.put(paramName, req.getParameter(paramName));
        });
        Model model = new Model();
        //supports에 의해 핸들러 타입이 특정되었으므로 형변환 가능
        PageHandler handler = (PageHandler) objHandler;

        //핸들러의 비즈니스로직을 실행하고, 결과물을 model에 담음
        String viewName = (String) handler.process(model, paramMap);
        
        //뷰와 모델을 담은 객체 생성해서 반환
        ModelAndView modelAndView = new ModelAndView(viewName, model);
        return modelAndView;
    }
}
