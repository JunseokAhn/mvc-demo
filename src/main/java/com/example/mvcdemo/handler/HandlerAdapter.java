package com.example.mvcdemo.handler;

import com.example.mvcdemo.ui.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;

public interface HandlerAdapter {
    boolean supports(Object handler);

    @Nullable
    Object handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
