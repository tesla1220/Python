package com.ohgiraffers.crud.menu.model.service;

import com.ohgiraffers.crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.crud.menu.model.dto.CategoryAndMenuDTO;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuAndCategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

    /*
    MenuService 클래스는 컨트롤러와 데이터 액세스 계층(Database Access Layer) 간의 중간 역할을 하며,
    비즈니스 로직을 처리하여 컨트롤러에 필요한 데이터를 제공
     */

@Service
public class MenuService {

    private final MenuMapper menuMapper;

    @Autowired
    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
        // new 키워드 사용하지 않고 생성자 주입
    }

    public List<MenuDTO> findAllMenus() {

        return menuMapper.findAllMenu();


        // findAllMenu()는 MenuMapper 인터페이스에 있는 메소드.
        // findAllMenu()는 이 MenuService에서 호출하고 있음
        // findAllMenu()는 menuMapper에서 동작 후 findAllMenus()를 호출하고 있는 Controller로 return함

        /* findAllMenus():
        모든 메뉴를 조회하는 메서드입니다.
        이 메서드는 MenuMapper를 통해 데이터베이스에서 모든 메뉴를 조회하여 반환합니다.
         */
    }

    public List<CategoryDTO> findAllCategory() {

        return menuMapper.findAllCategory();

        /* findAllCategory():
        모든 카테고리를 조회하는 메서드입니다.
        이 메서드는 마찬가지로 MenuMapper를 통해 데이터베이스에서 모든 카테고리를 조회하여 반환합니다.
         */

    }

    /* 필기.
     *   @Transactional 어노테이션은 스프링 프레임워크에서 제공하는 트랜잭션 관리 지원하는 어노테이션 이다.
     *   트렌젝션은 데이터베이스의 상태를 변화시키는 일(작업)을 하나의 단위로 묶는 작업을 의미한다.
     *   데이터 조작에 관련 된 작업이 일어날 때(c, u, d) 메소드 실행이 완료되면 commit,
     *   예외가 발생하게 되면 rollback
     *  */

    @Transactional
    public void registNewMenu(MenuDTO newMenu) {
        menuMapper.registNewMenu(newMenu);
    }

    /* registNewMenu(MenuDTO newMenu):
    새로운 메뉴를 등록하는 메서드입니다.
    이 메서드는 MenuDTO 객체를 받아서 MenuMapper를 통해 데이터베이스에 새로운 메뉴를 등록합니다.
    @Transactional 애노테이션을 사용하여 이 메서드가 하나의 트랜잭션으로 처리되도록 하고,
    메서드 수행 중에 예외가 발생하면 롤백되도록 설정합니다.
     */


    public List<MenuAndCategoryDTO> findAllMenuAndCategory() {

        return menuMapper.findAllMenuAndCategoryList();
    }


    public List<CategoryAndMenuDTO> findAllCategoryAndMenu() {

        return menuMapper.findAllCategoryAndMenu();
    }

    @Transactional
    public void deleteMenuByCode(int code) {
        menuMapper.deleteMenuByCode(code);
    }
}
