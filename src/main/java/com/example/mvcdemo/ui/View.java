package com.example.mvcdemo.ui;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class View {

    private String viewPath;

    public View(String viewPath) {
        this.viewPath= viewPath;
    }

    public void exposeModelAsRequestAttributes(Model model, HttpServletRequest req){
        Map<String, Object> paramMap = model.getValueMap();
        for (String key : paramMap.keySet()) {
            System.out.println(key + " : " + paramMap.get(key));
            req.setAttribute(key, paramMap.get(key));
        }
    }

    public void render(Model model, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        exposeModelAsRequestAttributes(model, req);
        req.getRequestDispatcher(viewPath).forward(req, res);
    }
}
