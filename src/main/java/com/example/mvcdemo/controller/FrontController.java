package com.example.mvcdemo.controller;

import com.example.mvcdemo.exception.ControllerNotFoundException;
import com.example.mvcdemo.exception.HandlerNotFoundException;
import com.example.mvcdemo.handler.Handler;
import com.example.mvcdemo.handler.HandlerAdapter;
import com.example.mvcdemo.handler.pagehandler.*;
import com.example.mvcdemo.handler.apihandler.SearchMember;
import com.example.mvcdemo.handler.apihandler.ApiHandlerAdapter;
import com.example.mvcdemo.repository.MemberRepository;
import com.example.mvcdemo.ui.ModelAndView;
import com.example.mvcdemo.ui.View;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/member/*")
public class FrontController extends HttpServlet {
    private final Map<String, Handler> handlerMap = new HashMap<>();
    private final List<HandlerAdapter> handlerAdapterList = new ArrayList<>();
    private final MemberRepository memberRepo = new MemberRepository();

    public FrontController() {
        // initHandlerMappings() -> (HandlerMapping) RequestMappingHandlerMapping...
        // RequestMappingHandlerMapping에 의해 HandlerMethod를 저장
        handlerMap.put("memberForm", new MemberForm());
        handlerMap.put("memberList", new MemberList(memberRepo));
        handlerMap.put("saveMember", new SaveMember(memberRepo));
        handlerMap.put("searchMember", new SearchMember(memberRepo));
        // initHandlerAdapters() -> (HandlerAdapter) RequestMappingHandlerAdapter...
        handlerAdapterList.add(new PageHandlerAdapter());
        handlerAdapterList.add(new ApiHandlerAdapter());
    }

    @Override
    @SneakyThrows
    public void service(HttpServletRequest req, HttpServletResponse res) {

        //모든 호출이 프론트컨트롤러(디스패쳐 서블릿)로 들어옴
        String requestURI = req.getRequestURI().replaceFirst("/member/", "");

        //해당 리소스를 관리하는 핸들러가 있는지 체크
        Handler handler = handlerMap.get(requestURI);
        if (handler == null) {
            throw new ControllerNotFoundException("화면을 찾을 수 없습니다.  requestURI : " + requestURI );
        }
        //해당 핸들러를 관리하는 어댑터가 있는지 체크
        // -> RequestMappingHandlerAdapter.supports()에서는 HandlerMethod타입을 체크
        // -> RequestMappingHandlerMapping과 연동
        HandlerAdapter handlerAdapter = null;
        for (HandlerAdapter element : handlerAdapterList) {
            boolean supports = element.supports(handler);
            if(supports)
                handlerAdapter = element;
        }
        if(handlerAdapter == null){
            throw new HandlerNotFoundException("핸들러를 찾을 수 없습니다." + requestURI);
        };
        //핸들러 프로세스(비즈니스로직) 실행 후 모델과 뷰패스를 갖고있는 모델앤뷰 반환
        Object result = handlerAdapter.handle(req, res, handler);

        // handle메서드가 ModelAndView를 반환하면 화면이동, String을 반환하면
        if (result instanceof ModelAndView) {
            ModelAndView modelAndView= (ModelAndView) result;
            //뷰 리졸버에서 물리적경로를 설정
            View view= jspViewResolver(modelAndView);
            //화면 이동
            view.render(modelAndView.getModel(), req, res);
        }
        if(result instanceof String){
            // 헤더 설정
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            // JSON 문자열을 출력
            PrintWriter out = res.getWriter();
            out.print(result);
            out.flush();
        }

    }

    private View jspViewResolver(ModelAndView modelAndView) {
        return new View("/WEB-INF/" + modelAndView.getName() + ".jsp");
    }
}