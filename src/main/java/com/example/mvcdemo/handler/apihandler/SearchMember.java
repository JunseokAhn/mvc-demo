package com.example.mvcdemo.handler.apihandler;

import com.example.mvcdemo.repository.MemberRepository;

import java.util.Map;
import java.util.Optional;

public class SearchMember implements ApiHandler {

    MemberRepository repository;

    public SearchMember(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object process(Map<String, String> paramMap) throws Exception {

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
            return repository.getMember(optionalId.get());
        }
        if (optionalId.isEmpty() && optionalName.isPresent()) {
            return repository.getMemberListByName(optionalName.get());
        }
        if (optionalId.isPresent() && optionalName.isPresent()) {
            return repository.getMember(optionalId.get());
        }

        return null;
    }
}