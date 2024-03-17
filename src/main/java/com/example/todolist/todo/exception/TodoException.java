package com.example.todolist.todo.exception;

import com.example.todolist.exception.CustomException;
import com.example.todolist.exception.ErrorCode;

public class TodoException extends CustomException {
    public TodoException(ErrorCode errorCode) {super(errorCode);}
}
