package com.example.mvcdemo.controller;

import com.example.mvcdemo.Member;
import com.example.mvcdemo.MemberRepository;
import com.example.mvcdemo.Model;

import java.util.List;
import java.util.Map;

public class MemberList implements Controller {

    private MemberRepository repository;

    public MemberList(MemberRepository repository){
        this.repository=repository;
    }

    @Override
    public String process(Model model, Map<String, String> paramMap) throws Exception {

        List<Member> memberList = repository.getMemberList();
        model.setValueMap("memberList", memberList);
        return "memberList";
    }

}
