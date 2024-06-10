package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryGenerator {

    // 정적 필드 : private static EntityManagerFactory factory 는 클래스 로딩 시점에 한 번 초기화되며, 이후 변경되지 않습니다.
    // EntityManagerFactory 는 인터페이스
    private static EntityManagerFactory factory
            = Persistence.createEntityManagerFactory("jpatest");


    // private 생성자: private EntityManagerFactoryGenerator()는 외부에서 이 클래스를 인스턴스화할 수 없도록 합니다.
    private EntityManagerFactoryGenerator() {}

    /* 상기에서 보듯이 접근제한자 때문에 이 클래스는 외부에서 접근 못함 그러나!!!!!
        getInstance() 메소드를 통해서만 이 클래스에 접근할 수 있도록 아래 메소드 생성 */

    // 정적 메소드: public static EntityManagerFactory getInstance()는 클래스의 유일한 인스턴스를 반환하는 메서드입니다.
    public static EntityManagerFactory getInstance() {
        return factory;
    }

    /* 싱글톤 패턴
        : 한 클래스에 대해 하나의 인스턴스만 생성하고, 이를 전역적으로 접근할 수 있도록 하는 디자인 패턴 */

    /* 이 클래스는 EntityManagerFactory 인스턴스를 하나만 생성하여 이를 공유하도록 보장합니다.
    이는 자원을 효율적으로 사용하고, 애플리케이션의 안정성을 높이는 데 도움이 됩니다.*/
}

