package com.example.servletdemo;

import java.util.List;

public interface FrontController {
    List<Member> getMemberList();
    long saveMember();
    Member getMember(long id);
}
