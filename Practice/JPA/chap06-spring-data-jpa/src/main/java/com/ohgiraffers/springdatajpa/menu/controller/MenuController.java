package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.model.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService service;

    @Autowired
    public MenuController(MenuService theService){
        this.service = theService;
    }


    @GetMapping("/{menuCode}")
    public String findByMenuCode(@PathVariable int menuCode, Model model){

        MenuDTO resultMenu = service.findMenuByMenuCode(menuCode);

        model.addAttribute("findByMenuCode", resultMenu);

        return "menu/detail";

    }

    @GetMapping("/list")
    public String menuList(Model model){

        List<MenuDTO> menuDTOList = service.findAll();

        model.addAttribute("menuDTOList" ,menuDTOList);

        return "menu/list";

    }


    @GetMapping("/querymethod")
    public void queryMethod() {}

    @GetMapping("/search")
    public String searchMenuByPrice(@RequestParam int menuPrice, Model model){

        List<MenuDTO> menuPriceResultList = service.findMenuByMenuPrice(menuPrice);

        model.addAttribute("menuPriceResultDTOList", menuPriceResultList);

        return "menu/searchResult";

    }

    @GetMapping("/regist")
    public void registMenuPage() {}

//
//    @PostMapping("/regist")
//    public String registMenu(String menuName, int menuPrice) {
//
//        MenuDTO newMenuDTO = service.registNewMenu(menuName, menuPrice);
//
//    }
//
//



}
