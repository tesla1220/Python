package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/list")
    public String findMenuList(Model model) {

        List<MenuDTO> menuList = menuService.findAllMenus();

        // Service에서 넘어온 findAllMenus()는 변수 menuList에 담기게 됨.

        model.addAttribute("menuList", menuList);

        return "menu/list";
        // 전체 메뉴를 model(정보 저장하는 곳)에 담아둔 다음 return으로 menu/list 에 갖다줌

        //현재 키 값 menuList 로 menu/list 명의 html 파일에서 사용할 수 있게끔 만들어줘야함

    }

    @GetMapping("regist")
    public void registPage() {}

    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();


    }
}
