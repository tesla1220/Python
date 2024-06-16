package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.menu.model.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.model.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    @GetMapping("/{menuCode}")
    public String findByMenuCode(@PathVariable int menuCode, Model model) {

        MenuDTO resultMenu = menuService.findMenuByMenuCode(menuCode);

        model.addAttribute("findByMenuCode", resultMenu);

        return "menu/detail";

    }

    @GetMapping("/list")
    public String menuList(Model model) {

        List<MenuDTO> menuDTOList = menuService.findAll();

        model.addAttribute("menuDTOList", menuDTOList);

        return "menu/list";

    }


    @GetMapping("/querymethod")
    public void queryMethod() {
    }

    @GetMapping("/search")
    public String searchMenuByPrice(@RequestParam int menuPrice, Model model) {

        List<MenuDTO> menuPriceResultList = menuService.findMenuByMenuPrice(menuPrice);

        model.addAttribute("menuPriceResultDTOList", menuPriceResultList);

        return "menu/searchResult";

    }


    @GetMapping("/regist")
    public void registMenuPage() {
    }



    @GetMapping(value = "/category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();
    }


    @PostMapping("/regist")
    public String registMenu(MenuDTO menuDTO) {

        System.out.println("menuName: " + menuDTO.getMenuName());
        System.out.println("menuPrice: " + menuDTO.getMenuPrice());
        System.out.println("categoryCode: " + menuDTO.getCategoryCode());
        System.out.println("orderableStatus: " + menuDTO.getOrderableStatus());


        menuService.registMenu(menuDTO);

        return "redirect:/menu/list";

    }

    @GetMapping("/modify")
    public void modifyMenuPage() {
    }

    @PostMapping("/modify")
    public String modifyMenu(MenuDTO modifyMenuDTO) {

        menuService.modifyMenu(modifyMenuDTO);

        return "redirect:/menu/" + modifyMenuDTO.getMenuCode();

    }

    @GetMapping("/delete")
    public void deleteMenuPage(){}

    @PostMapping("/delete")
    public String deleteMenu(@RequestParam int menuCode){

        menuService.deleteMenu(menuCode);

        return "redirect:/menu/list";
    }
}



