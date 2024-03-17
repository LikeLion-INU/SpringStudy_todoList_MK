package com.example.todolist.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다.");

    private final HttpStatus httpStatus;
    private final String msg;

}

