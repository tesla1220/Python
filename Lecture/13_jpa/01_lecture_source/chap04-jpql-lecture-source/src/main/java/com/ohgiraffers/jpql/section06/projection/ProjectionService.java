package com.ohgiraffers.jpql.section06.projection;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectionService {

    private ProjectionRepository repository;

    @Autowired
    public ProjectionService(ProjectionRepository repository){
        this.repository = repository;
    }

    @Transactional
    public List<Menu> singleEntityProjection() {

        List<Menu> menuList = repository.singleEntityProjection();

        Menu menu = repository.findMenu(1);
        System.out.println("menu : " + menu);

        menuList.get(7).setMenuName("새로운 이름");

        return menuList;


    }


    @Transactional
    public BiDirectionCategory biDirectionProjection(int menuCode) {

        BiDirectionCategory categoryOfMenu = repository.biDirectionProjection(menuCode);

        categoryOfMenu.getMenuList().forEach(System.out::println);

        return categoryOfMenu;

    }
}
