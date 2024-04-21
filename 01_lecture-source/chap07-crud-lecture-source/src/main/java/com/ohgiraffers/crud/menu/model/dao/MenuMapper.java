package com.ohgiraffers.crud.menu.model.dao;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<MenuDTO> findAllMenu();
    // findAllMenu() 이 메소드 이름이 실제로 동작할 쿼리문의 아이디가 됨

    /*
        이 findAllMenu()를 호출하고 있는 곳 -> MenuService
        public List<MenuDTO> findAllMenus() {

        return menuMapper.findAllMenu();
    }
    */

    /* findAllMenus():
    모든 메뉴를 조회하는 메서드입니다.
    데이터베이스에 저장된 모든 메뉴를 가져와서 MenuDTO 객체의 리스트로 반환합니다.
     */


    List<CategoryDTO> findAllCategory();

    /* findAllCategory():
    모든 카테고리를 조회하는 메서드입니다.
    데이터베이스에 저장된 모든 카테고리를 가져와서 CategoryDTO 객체의 리스트로 반환합니다.
     */


    void registNewMenu(MenuDTO newMenu);


        /* registNewMenu(MenuDTO newMenu):
    새로운 메뉴를 등록하는 메서드입니다.
    매개변수로 받은 MenuDTO 객체를 데이터베이스에 저장합니다. 반환값이 없으므로 void 타입입니다.
     */

    List<MenuDTO> findAllMenus();




    /* 이 인터페이스는 @Mapper 애노테이션을 사용하여 MyBatis에 의해 구현될 것을 나타냅니다.
    @Mapper 애노테이션을 붙인 인터페이스는 MyBatis에 의해 자동으로 구현체가 생성되고,
    해당 구현체는 SQL 쿼리를 실행하여 데이터베이스와 상호작용합니다.
    따라서 이 인터페이스를 사용하여 데이터베이스 작업을 수행할 수 있습니다.
     */
}

