package com.ohgiraffers.section03.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerGenerator {

    private static EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory("jpatest");

    private EntityManagerGenerator() {}

    // 외부에서도 manager 에 접근가능하도록 manager 인스턴스를 반환
    public static EntityManager getManagerInstance() {
        return entityManagerFactory.createEntityManager();
    }



}
