package com.ohgiraffers.section03.entity;

import jakarta.persistence.EntityManager;

public class EntityLifeCycle {

    private EntityManager manager;

    public EntityManager getManagerInstance() {
        return manager;  }


    public Menu findMenuByMenuCode(int menuCode) {

        manager = EntityManagerGenerator.getManagerInstance();

        return manager.find(Menu.class, menuCode);
    }


    /*      manager.find(Menu.class, menuCode);
    manager : manager 는 EntityManager 인스턴스야. 데이터베이스와 상호작용하는 데 사용돼.
    find 메소드 : find 는 EntityManager 의 메소드로, 데이터베이스에서 특정 엔티티를 기본 키로 검색하는 데 사용돼.
    Menu.class : Menu.class 는 우리가 찾고자 하는 엔티티의 클래스 타입을 나타내. 여기서는 Menu 라는 엔티티 클래스를 나타내고 있어.
    menuCode : menuCode 는 검색할 때 사용할 기본 키(Primary Key) 값을 나타내. 즉, 특정 menuCode 를 가진 Menu 엔티티를 찾기 위해 사용돼.
    */
}
