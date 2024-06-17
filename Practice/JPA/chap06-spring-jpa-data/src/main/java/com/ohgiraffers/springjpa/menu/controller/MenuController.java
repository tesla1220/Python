package com.ohgiraffers.springjpa.menu.controller;

import com.ohgiraffers.springjpa.menu.model.dto.MenuDTO;
import com.ohgiraffers.springjpa.menu.model.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService service;

    @Autowired
    public MenuController(MenuService theService){
        this.service = theService;
    }

    @GetMapping("/{menuCode}")
    public String findMenuByCode(@PathVariable int menuCode, Model model) {

        MenuDTO menuResultDTO = service.findMenuByCode(menuCode);

        model.addAttribute("menuResultDTO", menuResultDTO);

        return "menu/findbymenucode";

    }

}
