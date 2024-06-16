package com.ohgiraffers.springdatajpa.menu.model.service;

import com.ohgiraffers.springdatajpa.menu.entity.Category;
import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.model.dao.CategoryRepository;
import com.ohgiraffers.springdatajpa.menu.model.dao.MenuRepository;
import com.ohgiraffers.springdatajpa.menu.model.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public MenuService(MenuRepository menuRepository, CategoryRepository categoryRepository, ModelMapper theModelMapper){

        this.menuRepository = menuRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = theModelMapper;

    }

    public MenuDTO findMenuByMenuCode(int menuCode) {

        Menu foundMenu = menuRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(foundMenu, MenuDTO.class);


    }


    public List<MenuDTO> findAll() {

        List<Menu> menuList = menuRepository.findAll(Sort.by("menuCode").ascending());

        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());

    }


    public List<MenuDTO> findMenuByMenuPrice(int menuPrice) {

        List<Menu> menuResultEntity = menuRepository.findMenuByMenuPrice(menuPrice);

        return menuResultEntity.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());


    }


    @Transactional
    public void registMenu(MenuDTO menuDTO) {

        menuRepository.save(modelMapper.map(menuDTO, Menu.class));

    }

//    public List<CategoryDTO> findAllCategory() {
//
//        List<Category> categoryEntityList = categoryRepository.findAllCategory();
//
//        return categoryEntityList.stream()
//                .map(category -> modelMapper.map(category, CategoryDTO.class))
//                .collect(Collectors.toList());
//    }

    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList = categoryRepository.findAllCategory();

        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void modifyMenu(MenuDTO modifyMenuDTO) {

        Menu menuEntityResult = menuRepository.findById(modifyMenuDTO.getMenuCode()).orElseThrow(IllegalArgumentException::new);

        menuEntityResult = menuEntityResult.menuName(modifyMenuDTO.getMenuName()).builder();
        menuRepository.save(menuEntityResult);


    }

    @Transactional
    public void deleteMenu(int menuCode) {

        menuRepository.deleteById(menuCode);

    }
}


