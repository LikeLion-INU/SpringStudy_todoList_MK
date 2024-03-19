package com.example.todolist.erros.exception;

import com.example.todolist.erros.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class MemberException extends RuntimeException{
    private final ErrorCode errorCode;
}
