package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuAndCategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private static final Logger logger = LogManager.getLogger(MenuController.class);

    private final MenuService menuService;      // MenuService 를 사용하기 위해 의존성 주입
                                                // final 키워드를 붙여 menuService를 필드로 선언

    private final MessageSource messageSource;

    public MenuController(MenuService menuService, MessageSource messageSource) {         // 생성자 초기화
        this.menuService = menuService;
        this.messageSource = messageSource;
    }


    @GetMapping("/list")
    public String findMenuList(Model model){

        List<MenuDTO> menuList = menuService.findAllMenus();

        model.addAttribute("menuList", menuList);       // 위의 메소드 실행 결과는 현재 menuList에 담겨있으므로
                                                                    // 이 값을 model 에 담아줌

        return "menu/list";         // 컨트롤러의 역할인 뷰를 리턴
    }

    @GetMapping("/regitst")
    public void registPage() {}

    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();
    }

    @PostMapping("regist")
    public String registMenu(MenuDTO newMenu, RedirectAttributes rttr, Locale locale) {

        menuService.registNewMenu(newMenu);
        logger.info("Locale : {}", locale);
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("registMenu",  null, locale ));

        return "redirect:/menu/list";
    }

    @GetMapping("joinCategory/list")
    public String menuAndCategoryList(Model model) {

        List<MenuAndCategoryDTO> menuAndCategoryList = menuService.findAllMenuaAndCategory();

        model.addAttribute("menuAndCategoryList", menuAndCategoryList);

        return "menu/joinMenu";
    }

    @GetMapping("delete")
    public void delete() {

    }

    @PostMapping("/delete")
    public String deleteMenuByCode(@RequestParam("code") int code, RedirectAttributes rttr, Locale locale) {

        menuService.deleteMenuByCode(code);
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("deleteMenu", null, locale));



        return "redirect:/menu/list";
    }


}
