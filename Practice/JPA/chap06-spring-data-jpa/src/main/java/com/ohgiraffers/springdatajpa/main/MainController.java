package com.ohgiraffers.springdatajpa.main;

import jakarta.persistence.GeneratedValue;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/main"})
    public String main() {
        return "main/main";
    }

}
