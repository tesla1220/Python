package com.ohgiraffers.associationmapping.section03.bidirection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BiDirectionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Menu findMenu(int menuCode) {

        return entityManager.find(Menu.class, menuCode);    // "Menu 엔티티 클래스를 기반으로 menuCode 를 찾아줘"
    }

    public Category findCategory(int categoryCode) {

        return entityManager.find(Category.class, categoryCode);
    }

    public void saveMenu(Menu menu) {

        entityManager.persist(menu);
    }

    public void saveCategory(Category category) {

        entityManager.persist(category);
    }
}
