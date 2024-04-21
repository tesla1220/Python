package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("/menu")
public class MenuController {

    private static final Logger logger = LogManager.getLogger(MenuController.class);
    // Logger 는 인터페이스

    private final MenuService menuService;
    private final MessageSource messageSource;      // message 를 읽을 수 있는 객체

    @Autowired
    public MenuController(MenuService menuService, MessageSource messageSource) {
        this.menuService = menuService;
        this.messageSource = messageSource;
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
    /*    "/menu/category"요청을 잡을 컨트롤러 생성
       여기서 value 는 @RequestMapping에 이미 /menu 를 붙여놨기 때문에 "category"만 기재
       produces 로 데이터 타입과 데이터 인코딩 설정 제공 */
    @ResponseBody
    // @ResponseBody를 사용하면 스프링은 메서드가 반환하는 데이터를 HTTP 응답 본문에 직접 넣어 클라이언트에게 반환
    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();
    }

    @PostMapping("regist")
    public String registMenu(MenuDTO newMenu, RedirectAttributes rttr, Locale locale) {

        /* locale : 지역(나라) 에 대한 정보 다국어 처리와 관련 된 정보 */

        menuService.registNewMenu(newMenu);

        logger.info("Locale : {}", locale);

        rttr.addFlashAttribute("successMessage",
                messageSource.getMessage("registMenu",
                        new Object[]{newMenu.getName()}, locale));

        // getMessage는 첫 전달인자로 code를 사용. 이 code 에는 우리가 property 값으로 작성한 키 값을 기재한다.

        return "redirect:/menu/list";
        // redirect 로 list 에 보내 다시 조회함
    }
}

