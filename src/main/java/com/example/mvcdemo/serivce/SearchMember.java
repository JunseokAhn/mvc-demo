package com.example.mvcdemo.serivce;

import com.example.mvcdemo.entity.Member;
import com.example.mvcdemo.controller.Controller;
import com.example.mvcdemo.repository.MemberRepository;
import com.example.mvcdemo.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SearchMember implements Controller {

    MemberRepository repository;

    public SearchMember(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public String process(Model model, Map<String, String> paramMap) throws Exception {

        Optional<Long> optionalId;
        Optional<String> optionalName;
        try {
            optionalId = Optional.ofNullable(Long.valueOf(paramMap.get("id")));
            optionalName = Optional.ofNullable(paramMap.get("name"));

        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 파라미터 입력입니다.");
        }

        if (optionalId.isEmpty() && optionalName.isEmpty()) {
            throw new IllegalArgumentException("아이디를 입력해주세요.");
        }
        if (optionalId.isPresent() && optionalName.isEmpty()) {
            Member member= repository.getMember(optionalId.get());
            model.setValueMap("member", member);
        }
        if (optionalId.isEmpty() && optionalName.isPresent()) {
            List<Member> memberList = repository.getMemberListByName(optionalName.get());
            model.setValueMap("memberList", memberList);
        }
        if (optionalId.isPresent() && optionalName.isPresent()) {
            Member member= repository.getMember(optionalId.get());
            model.setValueMap("member", member);
        }

        return "memberInfo";
    }
}