package com.example.mvcdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(urlPatterns = "/hello")
public class HelloController extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        super.service(req, res);
        System.out.println("TRANSACTION COMING");
        Map<String, String> map = new HashMap<>();

        req.getHeaderNames().asIterator().forEachRemaining(name->{
            map.put(name, req.getHeader(name));
        });
        req.getParameterNames().asIterator().forEachRemaining(name -> {
            map.put(name, req.getParameter(name));
        });
        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
            req.setAttribute(key, map.get(key));
        }

        req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req,res);
    }
}
