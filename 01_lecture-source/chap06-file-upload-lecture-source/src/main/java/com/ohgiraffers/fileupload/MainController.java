package com.ohgiraffers.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")            // GetMapping 을 사용해서 url 접근
    public String main() {
        return "main";
    }
}
