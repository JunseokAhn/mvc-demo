package com.example.mvcdemo.handler.pagehandler;

import com.example.mvcdemo.entity.Member;
import com.example.mvcdemo.repository.MemberRepository;
import com.example.mvcdemo.ui.Model;

import java.util.List;
import java.util.Map;

public class MemberList implements PageHandler {

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
