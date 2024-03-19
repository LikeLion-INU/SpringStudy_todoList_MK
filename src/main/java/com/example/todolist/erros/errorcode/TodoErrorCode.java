package com.example.todolist.erros.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TodoErrorCode implements ErrorCode {
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없는 투두입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
