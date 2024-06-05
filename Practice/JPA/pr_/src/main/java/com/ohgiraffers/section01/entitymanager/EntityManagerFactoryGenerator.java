package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryGenerator {

    private static EntityManagerFactory factory
            // 인스턴스를 저장할 변수를 선언. 아직 인스턴스 생성하지 않음
            = Persistence.createEntityManagerFactory("jpatest");
            //

    private EntityManagerFactoryGenerator() {}

    public static EntityManagerFactory getInstance() {

        return factory;
    }


}
