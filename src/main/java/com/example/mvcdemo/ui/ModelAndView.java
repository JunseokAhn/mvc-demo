package com.example.mvcdemo.ui;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class ModelAndView {
    private Model model;
    private String name;
    private String url;
    public ModelAndView(String name, Model model) {
        this.model= model;
        this.name= name;
        this.url= "/WEB-INF/" + name + ".jsp";
    }

    public void render(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String, Object> paramMap = this.model.getValueMap();
        for (String key : paramMap.keySet()) {
            System.out.println(key + " : " + paramMap.get(key));
            req.setAttribute(key, paramMap.get(key));
        }
        req.getRequestDispatcher(this.url).forward(req,res);
    }
}
