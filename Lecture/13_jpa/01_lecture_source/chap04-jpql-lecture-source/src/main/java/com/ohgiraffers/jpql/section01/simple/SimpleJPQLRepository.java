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
        // m.menuName 으로 사용하는 이유

        TypedQuery<String> query = manager.createQuery(jpql, String.class);
        // 반환값은 반드시 <> 안의 타입과 일치해야 한다.

        // 반환값 담기. 여기선 한 행만 조회했으므로 결과가 1개
        String resultMenuName = query.getSingleResult();

        return resultMenuName;


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
