package com.example.mvcdemo.handler.pagehandler;

import com.example.mvcdemo.exception.MemberNotFoundException;
import com.example.mvcdemo.repository.MemberRepository;
import com.example.mvcdemo.ui.Model;

import java.util.Map;

public class SaveMember implements PageHandler {

    private MemberRepository memberRepository;

    public SaveMember(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public String process(Model model, Map<String, String> paramMap) throws MemberNotFoundException {
        String name = paramMap.get("name");
        String strAge = paramMap.get("age");

        if (name == null || strAge == null) {
            throw new IllegalArgumentException("잘못된 파라미터 입력입니다.");
        }

        int age;
        try {
            age = Integer.parseInt(strAge);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("나이는 정수로 입력해야 합니다.");
        }

        long id = memberRepository.saveMember(name, age);
        model.setValueMap("member", memberRepository.getMember(id));
        return "memberInfo";
    }
}

