package com.example.servletdemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    Map<Long, Member> memberRepo = new HashMap<>();
    long id= 0;
    String[] names = {"name0", "name1", "name2", "name3"};

    @Test
    void getMember() {
        Member member1 = Member.createMember(++id, names[1], 1);
        memberRepo.put(member1.getId(), member1);
        Member member2 = memberRepo.get(id);
        assertThat(member2.getName()).isEqualTo(names[1]);
    }


    @Test
    void getMemberList() {

        Member member1 = Member.createMember(++id, names[1], 1);
        Member member2 = Member.createMember(++id, names[2], 2);
        Member member3 = Member.createMember(++id, names[3], 3);
        memberRepo.put(member1.getId(), member1);
        memberRepo.put(member2.getId(), member2);
        memberRepo.put(member3.getId(), member3);

        List<Member> members = memberRepo.values().stream().toList();
        for (int i = 0; i <= 2; i++) {
            Assertions.assertThat(members.get(i).getName()).isEqualTo(names[i+1]);
            Assertions.assertThat(members.get(i).getAge()).isEqualTo(i+1);
        }
    }
}