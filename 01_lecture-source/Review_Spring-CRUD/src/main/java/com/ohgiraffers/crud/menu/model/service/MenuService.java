package com.ohgiraffers.crud.menu.model.service;

import com.ohgiraffers.crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        // findAllMenu()는 MenuService에서 호출하고 있음
        // findAllMenu()는 menuMapper에서 동작 후 findAllMenus()를 호출하고 있는 Controller로 return함


    }

    public List<CategoryDTO> findAllCategory() {

        return menuMapper.findAllCategory();
    }
}
