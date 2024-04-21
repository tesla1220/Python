package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("controller-null")
    public String nullPointerExceptionTest() {

        String str = null;
        System.out.println(str.charAt(0));

        return "/";
    }

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException exception) {

        System.out.println("controller 레벨의 exception 처리_NullPointerExceptionHandler");

        return "error/nullPointer";
    }

    @GetMapping("controller-user")
    public String userExceptionHandler() throws MemberRegistException {

        boolean check = true;

        if(check) {
            throw new MemberRegistException("MemberRegistException");
        }
        return "/";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionTest(MemberRegistException exception, Model model) {

        System.out.println("controller 레벨의 exception 처리_userExceptionTest");
        model.addAttribute("exception", exception);

        return "error/memberRegist";
    }


}
