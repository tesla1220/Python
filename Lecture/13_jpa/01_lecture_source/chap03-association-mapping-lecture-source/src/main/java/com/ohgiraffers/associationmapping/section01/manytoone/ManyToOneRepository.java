package com.ohgiraffers.associationmapping.section01.manytoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/* @Repository
 스프링 프레임워크에서 해당 클래스가 데이터 접근 계층을 담당하는 리포지토리(DAO, Data Access Object)임을 나타냅니다.
 스프링이 이 클래스를 스캔하여 빈으로 등록하고, 예외 변환(AOP를 사용하여 데이터 접근 관련 예외를 스프링의 데이터 접근 예외로 변환) 등의 처리를 할 수 있게 합니다.
 */

@Repository
public class ManyToOneRepository {

    @PersistenceContext // JPA 설정 파일(persistence.xml)이나 스프링 설정 파일에서 정의된 영속성 컨텍스트를 참조. 이를 통해 EntityManager 를 자동으로 주입받을 수 있음
    private EntityManager manager;

    public Menu find(int menuCode) {

        return manager.find(Menu.class, menuCode);
        // () 안에는 manager 에게 엔티티에 대한 정보(Menu.class)를 먼저 전달하고, 그 후에 우리가 전달하고 싶은 정보(menuCode) 명시
    }

    /* 필기 : Jpql
    *   Java Persistence Query Language
    *       - 객체지향 쿼리 언어
    *       - 객체지향 모델에 맞게 작성된 쿼리를 통해 엔티티를 대상으로 검색하고, 검색 결과를 토대로 객체를 조작할 수 있다.
    *       - join 문법이 sql 과는 다소 차이가 있으며, 직접 쿼리를 작성할 수 있는 문법을 제공한다.
    *       - ‼️‼️주의사항 : from 절에 기술할 테이블명에는 반드시 엔티티명을 작성해야 한다.‼️‼️
    *       - 쿼리문에서 변수의 값을 바인딩하기 위한 jpql 문법 => 콜론(:) ex) :menuCode
     * */
    public String findCategoryName(int menuCode) {

        String jpql = "SELECT c.categoryName "
                + "FROM menu_and_category m "
                + "JOIN m.category c "
                + "WHERE m.menuCode = :menuCode";

        return manager.createQuery(jpql, String.class)
                .setParameter("menuCode", menuCode)   // 그냥 menuCode 라고 하면 매니저가 식별하지 못하므로, "menuCode"는 우리가 전달한 menuCode야 라고 알려줌
                .getSingleResult();

    }

    public void save(Menu newMenu) {

        manager.persist(newMenu);

    }
}
