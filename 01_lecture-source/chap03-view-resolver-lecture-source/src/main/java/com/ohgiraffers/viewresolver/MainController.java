package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    // 아래 뜻 -> 최초로 시작하는 메인페이지
    // '/'나 '/main'으로 주소가 끝나는 경우 아래의 메인 메소드를 실행하겠따 -> main.html 파일을 보여주겠다.
    @RequestMapping(value = {"/","/main"})

    public String main() {

        return "main";
    }

}
