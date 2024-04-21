package com.ohgiraffers.review2_chap04exceptionhandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException exception) {

        System.out.println("Global Exception Handler_NullPointerException");

        return "error/nullPointer";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model, MemberRegistException exception) {

        System.out.println("Global Exception Handler_MemberRegist");
        model.addAttribute("exception", exception);

        return "error/memberRegist";
    }

    @ExceptionHandler(Exception.class)
    public String nullPointerExceptionHandler(Exception exception) {
        return "error/default";
    }

}
