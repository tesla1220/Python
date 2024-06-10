package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;



public class EntityManagerGeneratorTest {

    @Test
    @DisplayName("엔티티 매니저 팩토리 생성 확인하기")
    void testCreateFactory() {

        EntityManagerFactory factory = EntityManagerFactoryGenerator.getInstance();

        System.out.println("엔티티 매니저 팩토리 hashcode : " + factory.hashCode());

        Assertions.assertNotNull(factory);
        // factory 존재 확인

    }

    @Test
    @DisplayName("엔티티 매니저 팩토리 싱글톤 인스턴스 확인") // 팩토리를 두 개 만들어 두 개의 해시코드 비교해서 동일하면 싱글톤(하나로 공유됨 확인)
    void testFactoryIsSigleton() {

        // given : 팩토리1 만들음
        EntityManagerFactory factory1 = EntityManagerFactoryGenerator.getInstance();

        // when : 팩토리2 만들음
        EntityManagerFactory factory2 = EntityManagerFactoryGenerator.getInstance();


        // then
        System.out.println("factory1 hashcode : " + factory1.hashCode());
        System.out.println("factory2 hashcode : " + factory2.hashCode());

        // factory1 과 2가 동일한 지 확인 => 확인결과 : 동일함, 인스턴스가 같다 = 공장이 같다 = 공장 하나만 존재. 왜? 싱글톤으로 공장 생성했으니까!
        Assertions.assertEquals(factory1, factory2);

    }

    @Test
    @DisplayName("엔티티 매니저 생성 확인")
    void testCreateManager() {

        // EntityManager 인터페이스
        EntityManager manager = EntityManagerGenerator.getInstance();

        System.out.println("manager 의 해시코드 : " + manager.hashCode());

        Assertions.assertNotNull(manager);


        /* 인터페이스: EntityManager 는 인터페이스입니다.
        인터페이스는 메서드의 선언만을 포함하고, 실제 구현은 인터페이스를 구현하는 클래스에서 이루어집니다. 인터페이스는 "어떻게 동작해야 하는지"를 정의합니다.

        인스턴스: EntityManager 의 인스턴스는 인터페이스를 구현한 클래스의 실제 객체입니다.
        EntityManagerGenerator.getInstance()는 이러한 객체를 반환합니다.*/
    }



    @Test
    @DisplayName("엔티티 매니저 스코프 확인 = 인스턴스가 어떤 식으로 유지되어 있는 지 확인")
    void testManagerLifeCycle() {

        // given
        EntityManager manager1 = EntityManagerGenerator.getInstance();

        // when
        EntityManager manager2 = EntityManagerGenerator.getInstance();

        // then
        System.out.println("manager1 의 hashcode : " + manager1.hashCode());
        System.out.println("manager2 의 hashcode : " + manager2.hashCode());

        Assertions.assertNotEquals(manager1, manager2);



    }
}
