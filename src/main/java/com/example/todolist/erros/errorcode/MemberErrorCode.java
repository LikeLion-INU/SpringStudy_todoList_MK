package com.example.todolist.erros.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode{

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없는 회원입니다."),
    MEMBER_VALIDATION(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다."),
    MEMBER_LOGIN_ERROR(HttpStatus.FORBIDDEN, "없는 아이디거나 일치하지 않은 비밀번호 입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
