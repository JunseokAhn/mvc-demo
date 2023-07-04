package com.example.mvcdemo.servlet;

import com.example.mvcdemo.Model;

import java.util.Map;

public class MemberForm implements Controller {
    @Override
    public String process(Model model, Map<String, String> paramMap) {
        return "memberForm";
    }
}
