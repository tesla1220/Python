package com.ohgiraffers.associationmapping.section02.onetomany;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class OneToManyRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public Category find(int categoryCode) {

        return entityManager.find(Category.class, categoryCode);        // 엔티티에 대한 정보, 엔티티를 식별할 수 있는 것(여기선 categoryCode)
    }

    public void regist(Category category) {
        entityManager.persist(category);        // 매니저한테 등록(persist)하라고 지시
    }
}
