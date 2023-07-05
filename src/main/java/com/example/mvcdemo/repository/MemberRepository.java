package com.example.mvcdemo.repository;

import com.example.mvcdemo.entity.Member;
import com.example.mvcdemo.exception.MemberNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemberRepository {
    private final Map<Long, Member> memoryRepo = new HashMap<>();
    private long id = 0;

    public long saveMember(String name, int age) {
        Member member = Member.createMember(++id, name, age);
        memoryRepo.put(id, member);
        return id;
    }

    public Member getMember(long id) throws MemberNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID는 1 이상이어야 합니다.");
        }

        Member member = memoryRepo.get(id);
        if (member == null) {
            throw new MemberNotFoundException("Member를 찾을 수 없습니다.");
        }

        if (member.getName() == null || member.getName().isEmpty()) {
            throw new IllegalArgumentException("Member 이름이 등록되지 않았습니다.");
        }
        return Member.createMember(member.getId(), member.getName(), member.getAge());
    }

    public List<Member> getMemberList() throws MemberNotFoundException {
        if(memoryRepo.isEmpty()){
            throw new MemberNotFoundException("멤버가 없습니다.");
        }
        List<Member> memberList = memoryRepo.values().stream().toList();
        return memberList;
    }

    public List<Member> getMemberListByName(String name) throws MemberNotFoundException {
        if(memoryRepo.isEmpty()){
            throw new MemberNotFoundException("멤버가 없습니다.");
        }
        List<Member> memberList= memoryRepo.values().stream().filter(member ->
                member.getName().equals(name)
        ).collect(Collectors.toList());
        return memberList;
    }
}
