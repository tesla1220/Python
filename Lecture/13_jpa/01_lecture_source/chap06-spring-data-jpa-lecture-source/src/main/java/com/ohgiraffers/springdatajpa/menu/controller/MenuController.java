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
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/modify")
    public void modifyMenuPage(){}
    // "/modify" 요청 오면 뷰 페이지 반환

    @PostMapping("/modify")
    public String modifyMenu(MenuDTO modifyMenuDTO){

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

    /* 🟠 log.info("조회한 내용 목록 : {}", menuList.getContent());

        이 코드는 로깅(logging)을 사용하여 정보를 기록하는 코드입니다.

        log
            이 코드에서 log는 로깅을 위한 객체나 인스턴스를 가리킵니다.
            대부분의 경우, Spring에서는 org.slf4j.Logger를 사용하여 로그를 기록합니다.

        .info
            log 객체의 메소드 중 하나로, 정보 레벨의 로그를 기록하는 메소드입니다.
            다른 로그 레벨로는 .debug, .warn, .error 등이 있습니다.
            각 레벨은 중요도에 따라 다르게 처리됩니다.

        "조회한 내용 목록 : {}"
            로그 메시지의 형식을 나타내며, 중괄호 {}는 그 자리에 변수나 값이 들어갈 위치를 나타냅니다.
            여기서는 메뉴 항목 목록을 나타내는 문자열입니다.

        menuList.getContent()
            로그 메시지의 매개변수로, 실제로 로그에 출력될 값입니다.
            menuList는 Spring의 Page 객체이며, getContent() 메소드는 현재 페이지의 내용을 반환합니다.
            즉, 이 코드는 현재 페이지에 있는 메뉴 항목들을 로그로 기록합니다.

        따라서 log.info("조회한 내용 목록 : {}", menuList.getContent());는
            "조회한 내용 목록 : [현재 페이지의 메뉴 항목들]" 형식으로 로그를 남기는 것입니다.
            이는 디버깅이나 모니터링 목적으로 특정 시점의 실행 상태를 추적하거나,
            실행 중인 애플리케이션의 상태를 파악하는 데 유용합니다. */
