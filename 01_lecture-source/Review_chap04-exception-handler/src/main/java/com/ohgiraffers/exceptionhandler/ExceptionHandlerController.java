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

        return null;
    }

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException exception){

        System.out.println("controller 레벨의 exception 처리");

        return "error/nullPointer";
    }

    @GetMapping("controller-user")
    public String userException() throws MemberRegistException {

      boolean check = true;

      if(check) {
          throw new MemberRegistException("userException throws MemberRegistException");
      }

      return "/";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionTest(MemberRegistException exception, Model model) {

        // 위의 exception은 MemberRegistException 클래스를 사용하고 있음.
        // 이 클래스는 상기의 "userException throws MemberRegistException" 내용을 담고 있다.
        // 해당 내용을 model 에 담아주면 아래의 model.addAttribute "exception"이라는 글자가 나왔을 때 exception의 값을 보여주게 함.

        System.out.println("controller 레벨의 exception 처리");
        model.addAttribute("exception", exception);

        return "error/memberRegist";
    }

}
