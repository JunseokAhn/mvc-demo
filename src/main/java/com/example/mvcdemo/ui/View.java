package com.example.mvcdemo.ui;

import com.example.mvcdemo.ui.Model;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class View {
    private String name;
    private String url;
    private Map<String, Object> paramMap;
    public View(String name, Model model) {
        this.name= name;
        this.paramMap= model.getValueMap();
        this.url= "/WEB-INF/" + name + ".jsp";
    }

    public void render(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        for (String key : paramMap.keySet()) {
            System.out.println(key + " : " + paramMap.get(key));
            req.setAttribute(key, paramMap.get(key));
        }
        req.getRequestDispatcher(this.url).forward(req,res);
    }
}
