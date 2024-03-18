package com.example.todolist.member.service;

import com.example.todolist.member.Member;
import com.example.todolist.member.dto.MemberRequestDto;
import com.example.todolist.member.dto.MemberResponseDto;

public interface MemberService {
    /*
    CREAT
     */
    MemberResponseDto.MemberJoinDto join(MemberRequestDto.MemberJoinDto member);

    MemberResponseDto.MemberLoginDto login(MemberRequestDto.MemberLoginDto member);

    /*
    READ
     */
    MemberResponseDto.MemberFindDto profile();

    /*
    UPDATE
     */
    MemberResponseDto.MemberUpdateDto update(MemberRequestDto.MemberUpdateDto member, Long id);

    /*
    DELETE
     */
    void delete(Long id);

}
