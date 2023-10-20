package com.example.mvcdemo.handler.pagehandler;

import com.example.mvcdemo.ui.Model;

import java.util.Map;

public class MemberForm implements PageHandler {
    @Override
    public Object process(Model model, Map<String, String> paramMap) throws Exception {
        return "memberForm";
    }

}
