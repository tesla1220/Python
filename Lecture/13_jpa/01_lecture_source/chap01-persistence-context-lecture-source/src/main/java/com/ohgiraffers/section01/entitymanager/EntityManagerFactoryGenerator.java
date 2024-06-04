package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryGenerator {

    // singleton 으로 만들기
    private static EntityManagerFactory factory
            = Persistence.createEntityManagerFactory("jpatest");

    private EntityManagerFactoryGenerator() {}

    // 상기 메소드들은 다 private 로 외부에서 접근 불가하기 때문에 외부 접근 가능하도록 아래 메소드 만들어줌
    public static EntityManagerFactory getInstance() {
        return factory;
        // getInstance 를 통해서만 접근 가능하도록 설정
    }


}
