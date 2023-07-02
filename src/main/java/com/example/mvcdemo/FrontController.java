package com.example.mvcdemo;

import java.util.List;

public interface FrontController {
    List<Member> getMemberList();
    long saveMember();
    Member getMember(long id);
}
