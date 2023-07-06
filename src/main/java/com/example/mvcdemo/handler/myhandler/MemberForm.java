package com.example.mvcdemo.handler.myhandler;

import com.example.mvcdemo.ui.Model;

import java.util.Map;

public class MemberForm implements MyHandler {
    @Override
    public String process(Model model, Map<String, String> paramMap) {
        return "memberForm";
    }
}
