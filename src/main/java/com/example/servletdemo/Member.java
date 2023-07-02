package com.example.servletdemo;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private long id;
    private String name;
    private int age;

    public static Member createMember(long id, String name, int age) {
        Member member = new Member();
        member.id= id;
        member.name= name;
        member.age= age;
        return member;
    }
}
