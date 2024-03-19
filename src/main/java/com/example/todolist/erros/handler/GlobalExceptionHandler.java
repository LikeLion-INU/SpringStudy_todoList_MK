package com.example.todolist.erros.handler;

import com.example.todolist.erros.errorcode.CommonErrorCode;
import com.example.todolist.erros.errorcode.ErrorCode;
import com.example.todolist.erros.errorcode.MemberErrorCode;
import com.example.todolist.erros.exception.MemberException;
import com.example.todolist.member.MemberController;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, MemberException.class})
    public ModelAndView handleIllegalArgument(IllegalArgumentException e){
        ModelAndView mav = new ModelAndView();
        log.warn("handleIllegalArgument",e);

        ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        mav.addObject("errorMessage", errorCode.getMessage());
        mav.setViewName("login");
        return mav;
    }

    @ExceptionHandler({NullPointerException.class})
    public ModelAndView handleNullPointer(NullPointerException e){
        ModelAndView mav = new ModelAndView();
        log.warn("nullPointerError", e);
        ErrorCode errorCode = MemberErrorCode.MEMBER_NOT_FOUND;
        mav.addObject("errorMessage",errorCode.getMessage());
        mav.setViewName("login");
        return mav;
    }
}
