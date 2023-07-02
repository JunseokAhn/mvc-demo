package com.example.mvcdemo;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {
    private Map<Long, Member> memberRepo = new HashMap<>();
    private long id=0;
    public long saveMember(String name, int age) {
        Member member = Member.createMember(++id, name, age);
        memberRepo.put(++id, member);
        return id;
    }

    public Member getMember(long id) {
        //TODO 밸리데이션 추가
        Member member = memberRepo.get(id);
        return Member.createMember(member.getId(), member.getName(), member.getAge());
    }

    public List<Member> getMemberList(){
        return memberRepo.values().stream().toList();
    }
}
