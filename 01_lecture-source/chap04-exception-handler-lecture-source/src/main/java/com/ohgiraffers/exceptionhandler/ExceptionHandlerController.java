package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Member;

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

        System.out.println("controller 레벨의 exception 처리");

        return "error/nullPointer";

    }

    @GetMapping("controller-user")
    public String userException() throws MemberRegistException {

        boolean check = true;

        if(check) {
            throw new MemberRegistException("나는 당신같은 회원 둔 적 없어");
        }
        return "/";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionTest(MemberRegistException exception, Model model) {

        System.out.println("controller 레벨의 exception 처리");
        model.addAttribute("exception", exception); // 왜 하는지?

        return "error/memberRegist";
    }
}
