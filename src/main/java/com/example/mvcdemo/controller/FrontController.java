package com.example.mvcdemo.controller;

import com.example.mvcdemo.exception.ControllerNotFoundException;
import com.example.mvcdemo.exception.HandlerNotFoundException;
import com.example.mvcdemo.handler.HandlerAdapter;
import com.example.mvcdemo.handler.myhandler.*;
import com.example.mvcdemo.handler.yourhandler.YourHandlerAdapter;
import com.example.mvcdemo.repository.MemberRepository;
import com.example.mvcdemo.ui.View;
import com.example.mvcdemo.ui.ModelAndView;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/member/*")
public class FrontController extends HttpServlet {
    private final Map<String, MyHandler> handlerMap = new HashMap<>();
    private final List<HandlerAdapter> handlerAdapterList = new ArrayList<>();
    private final MemberRepository memberRepo = new MemberRepository();

    public FrontController() {
        handlerAdapterList.add(new MyHandlerAdapter());
        handlerAdapterList.add(new YourHandlerAdapter());
        handlerMap.put("memberForm", new MemberForm());
        handlerMap.put("memberList", new MemberList(memberRepo));
        handlerMap.put("saveMember", new SaveMember(memberRepo));
        handlerMap.put("searchMember", new SearchMember(memberRepo));
    }

    @Override
    @SneakyThrows
    public void service(HttpServletRequest req, HttpServletResponse res) {

        String requestURI = req.getRequestURI().replaceFirst("/member/", "");

        MyHandler handler = handlerMap.get(requestURI);
        if (handler == null) {
            throw new ControllerNotFoundException("화면을 찾을 수 없습니다.  requestURI : " + requestURI );
        }

        HandlerAdapter handlerAdapter = null;
        for (HandlerAdapter element : handlerAdapterList) {
            boolean supports = element.supports(handler);
            if(supports)
                handlerAdapter = element;
        }
        if(handlerAdapter == null){
            throw new HandlerNotFoundException("핸들러를 찾을 수 없습니다." + requestURI);
        };

        ModelAndView modelAndView = handlerAdapter.handle(req, res, handler);
        View view= jspViewResolver(modelAndView);
        view.render(modelAndView.getModel(), req, res);
    }

    private View jspViewResolver(ModelAndView modelAndView) {
        return new View("/WEB-INF/" + modelAndView.getName() + ".jsp");
    }
}
