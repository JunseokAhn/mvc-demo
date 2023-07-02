package com.example.servletdemo.servlet;

import com.example.servletdemo.Dispatcher;
import com.example.servletdemo.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@WebServlet(urlPatterns = "/memberForm")
@RequiredArgsConstructor
public class MemberForm extends HttpServlet {

    private final MemberRepository memberRepository;
    private final Dispatcher dispatcher;
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        dispatcher.forward("memberForm", req,res);
    }
}
