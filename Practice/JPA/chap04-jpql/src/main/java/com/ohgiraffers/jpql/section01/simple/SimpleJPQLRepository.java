package com.ohgiraffers.jpql.section01.simple;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SimpleJPQLRepository {

    @Autowired
    private EntityManager manager;

    public String selectSingleMenuByTypedQuery() {

        String jpql = "SELECT m.menuName FROM section01Menu as m WHERE m.menuCode =8";

        TypedQuery<String> query = manager.createQuery(jpql, String.class);

        String resultMenuName = query.getSingleResult();

        return resultMenuName;

    }

    public Menu findMenu(int menuCode) {

        return manager.find(Menu.class, menuCode);
    }




    public Menu selectSingleRowByTypedQuery() {

        String jpql = "SELECT m from section01Menu as m WHERE m.menuCode=8";

        TypedQuery<Menu> query = manager.createQuery(jpql, Menu.class);

        Menu resultMenu = query.getSingleResult();

        return resultMenu;
    }

    public Object selectSingleMenuByQuery() {

        String jpql = "SELECT m.menuName FROM section01Menu as m WHERE m.menuCode = 8";

        Query query = manager.createQuery(jpql);
        Object resultMenuName = query.getSingleResult();

        return resultMenuName;


    }


    public List<Menu> multiRowByTypedQuery() {

        String jpql = "SELECT m FROM section01Menu as m";

        TypedQuery<Menu> query = manager.createQuery(jpql, Menu.class);

        List<Menu> resultMenu = query.getResultList();

        return resultMenu;


    }
}
