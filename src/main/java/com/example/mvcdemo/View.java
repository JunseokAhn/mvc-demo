package com.example.mvcdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class View {
    private String name;
    private String url;
    private Model model;
    public View(String name, Model model) {
        this.name= name;
        this.model= model;
        this.url= "/WEB-INF/" + name + ".jsp";
    }

    public void render(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher(this.url).forward(req,res);
    }
}
