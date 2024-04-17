package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* 필기
*   전역에 대한 예외 처리를 담당하는 친구이다.
*   여러 컨트롤러에서 발생할 수 있는 예외(Exception)를 한 곳에서 처리할 수 있다.
*   코드의 중복을 줄이고 하나의 중앙 클래스에서 효율적으로 관리하기 위해 사용된다.
* */

@ControllerAdvice
public class GlobalExceptionHandler {

    /* 필기
    *   이 곳은 ExceptionHandler 들이 모임하는 장소
    * */

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerException(NullPointerException exception) {

        System.out.println("Global 레벨의 exception 처리");

        return "error/nullPointer";
    }


}
