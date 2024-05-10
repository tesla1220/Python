package com.ohgiraffers.crud.model.service;

import com.ohgiraffers.crud.model.dao.MenuMapper;
import com.ohgiraffers.crud.model.dto.CategoryDTO;
import com.ohgiraffers.crud.model.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuMapper menuMapper;

    @Autowired
    public MenuService(MenuMapper menuMapper){
        this.menuMapper = menuMapper;
    }


    public List<MenuDTO> findAllMenus() {

        return menuMapper.findAllMenus();
    }

    public List<CategoryDTO> findAllCategory(){

        return menuMapper.findAllCategory();
    }
}
