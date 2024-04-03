package com.example.todolist.erros.handler;

import com.example.todolist.erros.errorcode.CommonErrorCode;
import com.example.todolist.erros.errorcode.ErrorCode;
import com.example.todolist.erros.errorcode.MemberErrorCode;
import com.example.todolist.erros.exception.MemberException;
import com.example.todolist.member.MemberController;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e){
        log.warn("handleIllegalArgument",e);

        ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        return ResponseEntity.status(errorCode.getHttpStatus().value())
                .body(errorCode.getMessage());
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<?> handleNullPointer(NullPointerException e){
        log.warn("nullPointerError", e);
        ErrorCode errorCode = MemberErrorCode.MEMBER_NOT_FOUND;
        return ResponseEntity.status(errorCode.getHttpStatus().value())
                .body(errorCode.getMessage());
    }

    @ExceptionHandler({MemberException.class})
    public ResponseEntity<?> handleMemberException(MemberException e){
        log.warn("MemberException",e);
        return ResponseEntity.status(e.getErrorCode().getHttpStatus().value())
                .body(e.getMessage());
    }

}
