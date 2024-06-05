package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EntityManagerGenerator {


//    public EntityManagerGenerator(){}

    public static EntityManager getInstance() {

        EntityManagerFactory factory = EntityManagerFactoryGenerator.getInstance();
        // EntityManagerFactoryGenerator 에 이미 getInstance 만들어놓음
        return factory.createEntityManager();
        // 공장 먼저 만들고 일할 사람(Manager) 만들음


    }
}
