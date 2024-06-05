package com.ohgiraffers.section03.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerGenerator {

    private static EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory("jpatest");

    // 기본 생성자의 접근제한자 Private
    private EntityManagerGenerator() {}

    // 외부에서 접근 가능하도록
    public static EntityManager getManagerInstance() {
        return entityManagerFactory.createEntityManager();
    }
}
