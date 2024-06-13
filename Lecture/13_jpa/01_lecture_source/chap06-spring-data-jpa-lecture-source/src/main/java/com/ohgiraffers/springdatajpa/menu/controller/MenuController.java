package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.common.Pagination;
import com.ohgiraffers.springdatajpa.common.PagingButton;
import com.ohgiraffers.springdatajpa.menu.model.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.model.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }


    @GetMapping("/{menuCode}")
    public String findMenuByCode(@PathVariable int menuCode, Model model){

        // MenuDTO 에 담아줌
        MenuDTO resultMenu = menuService.findMenuByMenuCode(menuCode);

        // model 객체에 위 자료 담아줌
        model.addAttribute("menu", resultMenu);

        return "menu/detail";

    }

    @GetMapping("/list")
    public String findMenuList(Model model, @PageableDefault Pageable pageable) {

//        /* no Paging 버젼 */
//        List<MenuDTO> menuDTOList = menuService.findMenuList();
//
//        model.addAttribute("menuList", menuDTOList);


        /* Paging 버젼 */
        log.info("pageable : {}", pageable);

        Page<MenuDTO> menuList = menuService.findMenuList(pageable);

        // 조회한 내용 목록 : {} => {}  안에 menuList.getContent() 가 들어간다
        log.info("조회한 내용 목록 : {}", menuList.getContent());

        log.info("총 페이지수 : {}", menuList.getTotalPages());
        log.info("총 메뉴의 수 : {}", menuList.getTotalElements());
        log.info("해당 페이지에 표시될 요소의 수 : {}", menuList.getSize());
        log.info("해당 페이지의 실제 요소들의 개수 : {}", menuList.getNumberOfElements());
        log.info("첫 페이지 여부 : {}", menuList.isFirst());
        log.info("마지막 페이지 여부 : {}", menuList.isLast());
        log.info("정렬 방식: {}", menuList.getSort());
        log.info("여러 페이지 중 현재 인덱스 : {}", menuList.getNumber());

        PagingButton paging = Pagination.getPagingButtonInfo(menuList);

        model.addAttribute("menuList", menuList);
        model.addAttribute("paging", paging);

        return "menu/list";

    }

    @GetMapping("/querymethod")
    public void queryMethodPage() {}
    // 이 식 그대로면 "/querymethod" 페이지로 바로 감

    @GetMapping("/search")
    public String findByMenuPrice(@RequestParam int menuPrice, Model model) {

        List<MenuDTO> menuDTOList = menuService.findByMenuPrice(menuPrice);

        model.addAttribute("menuList", menuDTOList);
        model.addAttribute("menuPrice", menuPrice);

        return "menu/searchResult";

    }

    @GetMapping("/regist")
    public void registMenuPage() {}
    // 페이지 리턴


    @GetMapping(value = "/category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();
    }

    @PostMapping("/regist")
    public String registNewMenu(MenuDTO menuDTO){

        menuService.registNewMenu(menuDTO);

        return "redirect:/menu/list";

    }

}
