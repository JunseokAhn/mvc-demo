package com.example.servletdemo.servlet;

import com.example.servletdemo.Member;
import com.example.servletdemo.MemberRepository;
import com.example.servletdemo.Dispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@WebServlet(urlPatterns = "/saveMember")
@RequiredArgsConstructor
public class SaveMember extends HttpServlet {

    private final MemberRepository memberRepository;
    private final Dispatcher dispatcher;
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String ageStr = req.getParameter("age");

        if (name == null || ageStr == null) {
            System.out.println("파라미터 입력 재확인");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            System.out.println("나이는 정수로 입력해야 합니다.");
            return;
        }

        long id = memberRepository.saveMember(name, age);

//        dispatcher.forward("memberList", req,res);
        Member member = memberRepository.getMember(id);
        req.setAttribute("member", member);
        dispatcher.forward("memberInfo", req,res);
    }
}