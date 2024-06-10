package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EntityManagerGenerator {

    // EntityManager 인터페이스 사용해 getInstance 메소드 생성
    public static EntityManager getInstance(){

        // EntityManagerFactory 인터페이스 이용
        EntityManagerFactory factory = EntityManagerFactoryGenerator.getInstance();

        return factory.createEntityManager();

    }
}
