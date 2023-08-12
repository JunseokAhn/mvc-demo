package com.example.mvcdemo.ui;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;
import java.util.Map;

@Data
public class ModelAndView {
    private Model model;
    private String name;
    public ModelAndView(String name, Model model) {
        this.name= name;
        this.model= model;
    }

}
