package com.example.mvcdemo.servlet;

import com.example.mvcdemo.Dispatcher;
import com.example.mvcdemo.MemberRepository;
import com.example.mvcdemo.Member;
import com.example.mvcdemo.Model;
import jakarta.servlet.ServletException;
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
    private final Model model;
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
