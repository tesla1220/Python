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


    /* @ModelAttribute 의 사용해도 똑같음
            @ModelAttribute 어노테이션은 폼 데이터를 객체로 바인딩하는 데 사용됩
            이는 주로 폼 제출을 처리할 때 사용되며, 자동으로 폼 데이터의 필드를 객체의 필드에 매핑함.
            그래서 아래와 같이 @ModelAttribute 애노테이션 사용해 메소드 설정해도 똑같이 작동

            @PostMapping("/regist")
            public String registMenu(@ModelAttribute MenuDTO menuDTO) {
                service.registMenu(menuDTO);
                return "redirect:/menu/list";
            }
    */

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

/* 일반적인 스프링 MVC의 작동 방식은 다음과 같습니다:

    @Controller와 View Resolver:
        @Controller 애노테이션이 붙은 클래스의 메서드는 일반적으로 View 이름을 반환하여 해당 View를 렌더링합니다.
        이를 위해 View Resolver가 사용되어 Controller에서 반환한 View 이름에 해당하는 실제 View (HTML 파일 등)를 찾아 클라이언트에게 보여줍니다.

    @ResponseBody 사용:
        하지만 @ResponseBody를 사용하면 메서드가 반환하는 데이터가 HTTP 응답의 본문으로 사용됩니다.
        이때 스프링 MVC는 데이터를 직접 클라이언트에게 전송하며, View Resolver는 사용되지 않습니다.
        따라서 @ResponseBody를 사용하면 HTML 파일을 따로 만들지 않아도 됩니다.
        대신에 메서드가 반환하는 데이터를 클라이언트에게 JSON 형식으로 전달할 수 있습니다.
        이는 주로 단순한 데이터 전송이 목적인 API 개발에서 매우 유용합니다. */

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



