package com.example.mvcdemo.controller;

import com.example.mvcdemo.repository.MemberRepository;
import com.example.mvcdemo.exception.ControllerNotFoundException;
import com.example.mvcdemo.serivce.MemberForm;
import com.example.mvcdemo.serivce.MemberList;
import com.example.mvcdemo.serivce.SaveMember;
import com.example.mvcdemo.serivce.SearchMember;
import com.example.mvcdemo.ui.Model;
import com.example.mvcdemo.ui.View;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/member/*")
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

        String requestURI = req.getRequestURI().replaceFirst("/member/", "");

//        if(requestURI.equals("/")){
//            req.getRequestDispatcher("/static/index.html").forward(req,res);
//            return;
//        }

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
