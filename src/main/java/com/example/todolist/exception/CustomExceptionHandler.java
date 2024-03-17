package com.example.todolist.exception;

import com.example.todolist.exception.entity.ErrorResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
//모든 Controller 즉, 전역에서 발생할 수 있는 예외를 잡아 처리
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    //발생한 CustomException 예외를 잡아서 하나의 메소드에서 공통 처리
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(CustomException e){
        return ErrorResponseEntity.toResponseEntity(e.getErrorCode());
    }
    // 즉, 모든 컨트롤러에서 발생하는 CustomExceptino을 catch한다.
}
