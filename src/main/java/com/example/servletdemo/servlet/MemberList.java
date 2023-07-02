package com.example.servletdemo.servlet;

import com.example.servletdemo.Dispatcher;
import com.example.servletdemo.Member;
import com.example.servletdemo.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/memberList")
@RequiredArgsConstructor
public class MemberList extends HttpServlet {

    private final MemberRepository memberRepository;
    private final Dispatcher dispatcher;
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            List<Member> memberList = memberRepository.getMemberList();
            req.setAttribute("memberList", memberList);
            dispatcher.forward("memberList", req, res);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "오류 발생.");
            dispatcher.forward("errorPage", req, res);
        }
    }
}
