package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryAndMenuDTO;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuAndCategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    // Logger 는 인터페이스

    private final MenuService menuService;      // MenuService 는 menu 와 관련된 비지니스 로직 처리
    private final MessageSource messageSource;      // message 를 읽을 수 있는 객체

    @Autowired
    public MenuController(MenuService menuService, MessageSource messageSource) {
        this.menuService = menuService;
        this.messageSource = messageSource;
    }

    @GetMapping("/list")
    public String findMenuList(Model model) {

        List<MenuDTO> menuList = menuService.findAllMenus();

        // Service 에서 넘어온 findAllMenus()는 변수 menuList에 담기게 됨.

        model.addAttribute("menuList", menuList);

        return "menu/list";
        // 전체 메뉴를 model(정보 저장하는 곳)에 담아둔 다음 return으로 menu/list 에 갖다줌

        //현재 키 값 menuList 로 menu/list 이름의 html 파일에서 사용할 수 있게끔 만들어줘야함

    }

    /*
    findMenuList(): HTTP GET 메서드를 처리하며 "/menu/list" 경로에 매핑
    메뉴 리스트를 조회하여 모델에 추가하고, "menu/list" 뷰를 반환
     */

    @GetMapping("regist")
    public void registPage() {}

    /* registPage(): HTTP GET 메서드를 처리하며 "regist" 경로에 매핑됩니다.
    아무런 로직 없이, 단순히 뷰를 반환합니다.*/


    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    /*    "/menu/category"요청을 잡을 컨트롤러 생성
       여기서 value 는 @RequestMapping에 이미 /menu 를 붙여놨기 때문에 "category"만 기재
       produces 로 데이터 타입과 데이터 인코딩 설정 제공 */
    @ResponseBody
    // @ResponseBody를 사용하면 스프링은 메서드가 반환하는 데이터를 HTTP 응답 본문에 직접 넣어 클라이언트에게 반환
    // 즉, html에 나오는 바디 태그가 아니라 http에서 body 파트에 내가 데이터를 직접 넣어주겠다는 의미

    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();
        //
    }

    /* findCategoryList():
    HTTP GET 메서드를 처리하며 "category" 경로에 매핑되고, JSON 형식의 데이터를 반환합니다.
    메뉴 카테고리 리스트를 조회하여 반환합니다. */


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

    /* registMenu():
    HTTP POST 메서드를 처리하며 "regist" 경로에 매핑됩니다.
    새로운 메뉴를 등록하고, 성공 메시지를 Flash 속성으로 추가한 후 "/menu/list" 경로로 리다이렉션합니다.
     */


    /* 이 컨트롤러 클래스는 MenuService와 MessageSource를 의존성으로 주입받습니다.
        MenuService는 메뉴와 관련된 비즈니스 로직을 처리하며,
        MessageSource는 다국어 메시지를 처리하기 위해 사용됩니다.
     */

    @GetMapping("joinCategory/list")
    public String joinCategoryList(Model model) {

        List<MenuAndCategoryDTO> menuAndCategoryList = menuService.findAllMenuAndCategory();

        model.addAttribute("menuAndCategoryList", menuAndCategoryList);

        return "menu/joinMenu";
    }


    @GetMapping("/joinCategory/rightList")
    public String categoryAndMenu(Model model){
        List<CategoryAndMenuDTO> categoryAndMenuDTO = menuService.findAllCategoryAndMenu();
    //
        model.addAttribute("categoryAndMenu",categoryAndMenuDTO);


        return "menu/joinRight";
    }

    @GetMapping("delete")
    public void delete() {}

    @PostMapping("/delete")
    public String deleteMenuByCode(@RequestParam("code") int code, RedirectAttributes rttr, Locale locale) {

        menuService.deleteMenuByCode(code); // Mapper 까지 전달인자로 code를 데려가야 함
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("deleteMenu", null, locale));

        return "redirect:/menu/list";       // 메뉴 삭제 후 전체 조회하는 페이지로 감
    }
 
}