package com.example.mvcdemo;

import com.example.mvcdemo.exception.ControllerNotFoundException;
import com.example.mvcdemo.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/*")
public class FrontController extends HttpServlet {
    private Map<String, Controller> controllerRepo = new HashMap<>();
    private MemberRepository memberRepo = new MemberRepository();

    public FrontController() {
        controllerRepo.put("memberForm", new MemberForm());
        controllerRepo.put("memberList", new MemberList(memberRepo));
        controllerRepo.put("saveMember", new SaveMember(memberRepo));
        controllerRepo.put("searchMember", new SearchMember(memberRepo));
    }

    @Override
    @SneakyThrows
    public void service(HttpServletRequest req, HttpServletResponse res) {

        /*Todo urlPatterns "/*"로 인해 재귀현상 발생.
            /static/index.html
            /favicon.ico
            /error
        */
        String requestURI = req.getRequestURI();
        if(requestURI.equals("/")){
            req.getRequestDispatcher("/static/index.html").forward(req,res);
            return;
        }
        Controller controller = controllerRepo.get(requestURI);
        if (controller == null) {
            throw new ControllerNotFoundException("화면을 찾을 수 없습니다.  requestURI : " + requestURI );
        }
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(paramName -> {
            paramMap.put(paramName, req.getParameter(paramName));
        });
        Model model = new Model();
        String viewName = controller.process(model, paramMap);
        View view = new View(viewName, model);
        view.render(req, res);
    }
}
