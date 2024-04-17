package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")   // Request 쓰는 이유/ 밑에 GetMapping도 있는데 중복되는 거 아닌지?
public class InterceptorController {

    @GetMapping("stopwatch")
    public String handlerMethod() throws InterruptedException {

        System.out.println("핸들러 메소드 호출함");

        Thread.sleep(3000);

        return "result";
    }


}
