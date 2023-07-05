package com.example.mvcdemo.serivce;

import com.example.mvcdemo.controller.Controller;
import com.example.mvcdemo.ui.Model;

import java.util.Map;

public class MemberForm implements Controller {
    @Override
    public String process(Model model, Map<String, String> paramMap) {
        return "memberForm";
    }
}
