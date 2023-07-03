package com.example.mvcdemo.servlet;

import com.example.mvcdemo.Dispatcher;
import com.example.mvcdemo.Member;
import com.example.mvcdemo.MemberRepository;
import com.example.mvcdemo.Model;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@WebServlet(urlPatterns = "/searchMember")
@RequiredArgsConstructor
public class SearchMember extends HttpServlet {
    private final MemberRepository memberRepository;
    private final Dispatcher dispatcher;
    private final Model model;
    private Member member;
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        super.service(req, res);
        Long id = Long.valueOf(req.getParameter("id"));
        String name = (String) req.getParameter("name");
        Optional<Long> optionalId= Optional.ofNullable(id);
        Optional<String> optionalName= Optional.ofNullable(name);
        if(optionalId.isEmpty() && optionalName.isEmpty()){
            System.out.println("파라미터 입력 오류");
            return;
        }
        if(optionalId.isPresent() && optionalName.isEmpty()){
            member = memberRepository.getMember(id);
        }
        if(optionalId.isEmpty() && optionalName.isPresent()){
            //TODO 구현
        }
        if(optionalId.isPresent() && optionalName.isPresent()){
            member = memberRepository.getMember(id);
        }
        Map<String, String> modelMap = model.getModelMap(member);
        for (String key : modelMap.keySet()) {
            req.setAttribute(key, modelMap.get(key));
            System.out.println(key + ": "+ modelMap.get(key));
        }

        dispatcher.forward("memberInfo", req,res);
    }
}