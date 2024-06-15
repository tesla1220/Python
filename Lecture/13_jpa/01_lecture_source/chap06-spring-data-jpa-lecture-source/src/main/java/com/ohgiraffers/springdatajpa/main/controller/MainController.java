package com.ohgiraffers.springdatajpa.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/main"})
    public String main(){
        return "main/main";


    }

}


        /*  @GetMapping(value = {"/", "/main"})
        웹 애플리케이션의 루트 URL ("/")과 "/main" URL 로 들어오는 GET 요청을 처리하여, "main/main"이라는 뷰(view)를 반환하도록 설정 */