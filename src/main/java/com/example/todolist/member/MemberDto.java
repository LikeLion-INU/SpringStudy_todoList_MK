package com.example.todolist.member;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private String memberId;
    private String memberPw;
    private String memberName;
}
