package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullpointerExceptionHandler(NullPointerException exception){

        System.out.println("Global 레벨의 exception 처리");

        return "error/nullPointer";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionhandler(Model model, MemberRegistException exception){

        System.out.println("Global 레벨의 exception 처리 ");
        model.addAttribute("exception", exception);

        return "error/memberRegist";
    }

    @ExceptionHandler(Exception.class)
    public String nullPointerExceptionHandler(Exception exception){

        return "error/default";
    }

}
