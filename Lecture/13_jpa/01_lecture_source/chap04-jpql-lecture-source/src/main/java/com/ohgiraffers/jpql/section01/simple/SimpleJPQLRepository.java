package com.ohgiraffers.jpql.section01.simple;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository         // 데이터베이스와 직접 상호작용하는 건 Repository 이기 떄문에 SQL 문 여기서 작성
public class SimpleJPQLRepository {


    @Autowired
    private EntityManager manager;



    public String selectSingleMenuByTypedQuery() {

        String jpql = "SELECT m.menuName FROM section01Menu m WHERE m.menuCode = 8";
        // section01Menu 엔티티에서 menuCode 가 8인 메뉴의 이름(menuName)을 선택
        // m.menuName 으로 사용하는 이유 => 엔티티를 데이터베이스처럼 사용하고 있으므로, 엔티티에 기재된 명칭 사용


        // jpql 쿼리를 실행하는 TypedQuery 객체를 생성. 이 쿼리는 결과가 String 타입의 데이터를 반환
        TypedQuery<String> query = manager.createQuery(jpql, String.class);
        // 반환값은 반드시 <> 안의 타입과 일치해야 한다.
        // manager.createQuery(jpql, String.class)는 jpql 쿼리를 실행하는 TypedQuery 객체를 생성합니다.
        // 이 쿼리는 결과가 String 타입의 데이터를 반환할 것임을 나타냅니다.

        // 반환값 담기. 여기선 한 행만 조회했으므로 결과가 1개
        // query.getSingleResult() 메서드는 쿼리의 실행 결과를 하나의 String 값으로 반환합니다. 이 경우, menuName 필드의 값이 반환됩니다.
        String resultMenuName = query.getSingleResult();

        return resultMenuName;



      /*    🔼 요약
            section01Menu 엔티티에서 menuCode가 8인 메뉴의 이름을 String 타입으로 반환하는 방법을 보여줍니다.
            String.class는 쿼리 결과가 String 타입임을 명시적으로 지정하여, 타입 안전성을 보장합니다.*/

    }



    public Menu findMenu(int menuCode) {

        return manager.find(Menu.class, menuCode);
    }


    public Object selectSingleMenuByQuery() {

        String jpql = "SELECT m.menuName FROM section01Menu m WHERE m.menuCode = 8";
        Query query = manager.createQuery(jpql);
        Object resultMenuName = query.getSingleResult();

        return resultMenuName;

    }


    /*  순서
        1. JPQL 쿼리를 정의합니다.
        2. EntityManager 를 사용하여 TypedQuery 객체를 생성합니다.
        3.  쿼리를 실행하여 단일 Menu 객체를 가져옵니다.
        4. 조회된 Menu 객체를 반환합니다.  */


    public Menu selectSingleRowByTypedQuery() {

        String jpql = "SELECT m FROM section01Menu m WHERE m.menuCode = 8";
        // select 바로 뒤에 엔티티의 별칭(m)을 넣음

        TypedQuery<Menu> query = manager.createQuery(jpql, Menu.class);
        Menu resultMenu = query.getSingleResult();


        return resultMenu;

    }

    public List<Menu> selectMultipleRowByTypedQuery() {

        String jpql = "SELECT m FROM section01Menu m";
        TypedQuery<Menu> query = manager.createQuery(jpql, Menu.class); // 타입을 지정해주었기 떄문에(TypedQuery) Menu 클래스에 대한 정보 넘겨야함
        List<Menu> resultMenuList = query.getResultList();

        return resultMenuList;
    }

    public List<Menu> selectMultipleRowByQuery() {

        String jpql = "SELECT m FROM section01Menu m";
        Query query = manager.createQuery(jpql);    //  Query 이기 때문에 타입 지정해주지 않아으므로 jpql 뒤에 클래스 정보 넘길 게 없다.

        List<Menu> resultMenuList= query.getResultList();

        return resultMenuList;

    }

    public List<Integer> selectUseDistinct() {

        String jpql = "SELECT DISTINCT m.categoryCode FROM section01Menu m";
        // 중복된 카테고리 코드 제거함

        TypedQuery<Integer> query = manager.createQuery(jpql, Integer.class);
        List<Integer> resultCategoryCodeList = query.getResultList();

        return resultCategoryCodeList;
    }
}
