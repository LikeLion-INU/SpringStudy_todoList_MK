package com.example.todolist.member.Service;

import com.example.todolist.member.Member;
import com.example.todolist.member.MemberDto;
import org.springframework.stereotype.Service;

public interface MemberService {
    Member join(MemberDto member);

    String login(MemberDto member);

    Member profile();

    void update(MemberDto member, Long id);

    void delete(Long id);

}
