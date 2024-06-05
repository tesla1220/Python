package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


public class EntityManagerCRUD {

    private EntityManager manager;


    public EntityManager getManagerInstance() {

        return manager;
    }

    public Menu findMenuByMenuCode(int menuCode) {

        manager = EntityManagerGenerator.getManagerInstance();


        // "나는 메뉴라는 클래스를 기반으로 menuCode(아까 우리가 전달한 메뉴코드)를 찾을거야."
        return manager.find(Menu.class, menuCode);


    }

    public Long saveAndReturnAllCount(Menu newMenu) {

        manager = EntityManagerGenerator.getManagerInstance();

        // CUD 는 데이터베이스에 변화가 생기므로 transaction 만들어줌
        EntityTransaction transaction =  manager.getTransaction();

        // transaction 시작
        transaction.begin();

        // 매니저한테 전달받은 메뉴를 등록해달라고 요청
        manager.persist(newMenu);

        // 매니저에게 데이터베이스에 해당 데이터 밀어 넣으라고 명령 -> 데이터베이스에 변화 생김
        manager.flush();

        return getCount(manager);

    }

    private Long getCount(EntityManager manager) {

        // 쿼리문을 이용해서 행이 몇 개인지 조회를 하게 되면 결과는 하나이므로 getSingleResult 추가.
        return manager.createQuery("SELECT COUNT(*) FROM section02Menu", Long.class).getSingleResult();
    }

    public Menu modifyMenuName(int menuCode, String menuName) {

        // 메뉴 코드로 메뉴 찾기 위해 위에서 만들어놓은 메소드 findMenuByMenuCode 재사용
        Menu foundMenu = findMenuByMenuCode(menuCode);

        // 데이터베이스 변경사항 발생하므로 트랜잭션 생성
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();

        // 우리가 매개변수로 전달받은 내용을 세팅. 아직 데이터베이스에 반영하지 않은 상태
        foundMenu.setMenuName(menuName);

        // 데이터베이스에 반영
        manager.flush();

        // 이 메소드를 호출한 TestCode 로 foundMenu 값 넘겨줌
        return foundMenu;
    }
}
