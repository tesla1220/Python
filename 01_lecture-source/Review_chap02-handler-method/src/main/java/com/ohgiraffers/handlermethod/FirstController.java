package com.ohgiraffers.handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/first/*")
public class FirstController {

    @GetMapping("/regist")
    public void regist() {}

    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request) {

        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        String message = name + "을 신규 메뉴 목록의 " + price + "원으로 등록했습니다!";

        model.addAttribute("message", message);

       return "first/messagePrinter";
    }

    @GetMapping("/modify")
    public void modify() {}

    @PostMapping("modify")
    public String modifyMenuPrice(Model model,
                                  @RequestParam String modifyName,
                                  @RequestParam int modifyPrice) {

        String message = modifyName + "메뉴의 가격을" + modifyPrice + "로 변경했습니다!!";

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

}
