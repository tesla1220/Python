package com.ohgiraffers.crud.controller;

import com.ohgiraffers.crud.model.dto.CategoryDTO;
import com.ohgiraffers.crud.model.dto.MenuDTO;
import com.ohgiraffers.crud.model.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("/list")
    public String findMenuList(Model model){
        List<MenuDTO> menuList = menuService.findAllMenus();

        model.addAttribute("menuList", menuList);

        return "menu/list";

    }

    @GetMapping("regist")
    public void registPage(){}

    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();
    }

}
