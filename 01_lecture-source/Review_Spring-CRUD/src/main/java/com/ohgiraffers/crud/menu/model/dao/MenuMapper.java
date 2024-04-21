package com.ohgiraffers.crud.menu.model.dao;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;

import java.util.List;


public interface MenuMapper {

    List<MenuDTO> findAllMenu();    // findAllMenu() 이 메소드 이름이 실제로 동작할 쿼리문의 아이디가 됨


// 이 findAllMenu()를 호출하고 있는 곳 -> MenuService
//  public List<MenuDTO> findAllMenus() {
//
//    return menuMapper.findAllMenu();
//}

    List<CategoryDTO> findAllCategory();
}
