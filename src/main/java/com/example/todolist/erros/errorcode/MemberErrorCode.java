package com.example.todolist.erros.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode{

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없는 회원입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
