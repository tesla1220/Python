package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    /* 필기
    *   다른 컨트롤러에서 Exception 이 발생했을 때
    *   우리가 ExceptionHandlerController 에 정의해둔
    *   @ExceptionHandler 가 동작을 하지 않는다.
    * */

    @GetMapping("other-controller-null")
    public String otherNullPointerExceptionTest() {

        String str = null;
        System.out.println(str.charAt(0)); // null 익셉션 발생

        return "/";
    }

    @GetMapping("other-controller-user")
    public String otherUserExceptionTest() throws MemberRegistException {

        boolean check = true;

        if(check) {
            throw new MemberRegistException("또 와쪄여??");
        }
        return "/";
    }

    @GetMapping("other-controller-array")
    public String otherArrayExceptionTest() {

        double[] array = new double[0];
        System.out.println(array[0]);

        return "/";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model, MemberRegistException exception) {

        System.out.println("Global 레벨의 exception 처리");
        model.addAttribute("exception", exception);

        return "error/memberRegist";
    }

    /* 필기
        상위 타입(= 모든 예외들의 부모) Exception 을 이용하면
        구체적으로 작성하지 않은 타입의 예외가 발생하더라도 처리가 가능 -> default 처리 용도로 사용할 수 있다.
     */
    @ExceptionHandler(Exception.class)
    public String nullPointerExceptionHandler(Exception exception) {

        return "error/default";
    }
}
