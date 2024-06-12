package com.ohgiraffers.jpql.section01.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@SpringBootTest
public class SimpleJPQLTests {


    /* 필기 : JPQL
    *   엔티티 객체를 중심으로 개발할 수 있는객체 지향 쿼리. SQL 보다 간결하며 특정 DBMS 에 "의존하지 않는다"
    *   find() 메소드를 통한 조회와 다르게, 항상 데이터베이스에 SQL 을 실행해서 결과를 조회한다.  */


    /* 필기 : JPA 의 공식 지원 기능
    *   - JPQL : 시초에 나왔던 기술로 아래 두 언어의 기본
    *   - Native SQL : JPQL 대신 직접적으로 SQL 을 사용
    *   - Criteria Query : JPQL 을 편하게 작성하도록 도와주는 API */


    /* 필기 : JPQL 의 기본 문법 (CRUD)
        🟡 조회 ( 수직 순서대로 ). [] 절은 선택
            SELECT 절
            FROM 절
            [WHERE 절]
            [GROUP BY 절]
            [HAVING 절]
            [ORDER BY 절]
        🟡 삽입
            EntityManager 가 제공하는 Persist() 메소드를 사용한다.
        🟡 수정
            UPDATE
            SET
            [WHERE]
        🟡 삭제
            DELETE
            FROM
            [WHERE]
      */


    /* 필기 : JPQL 사용 시 주의사항
        - 엔티티와 속성은 대소문자를 구분한다. (userId != userid)
        - 엔티티명은 클래스명이 아닌 엔티티명이다. (ex. 클래스명인 Menu 가 아닌 "section01Menu"가 엔티티명)
        - JPQL 은 별칭이 필수. 별칭없이 사용하면 에러 발생 */


    /* 필기 : JPQL 사용 방법
        1. 작성한 JPQL(문자열)을 entityManager.createQuery() 메소드를 통해 쿼리 객체로 만들어준다.
        2. 쿼리 객체는 TypedQuery, Query 두 가지가 있다.
            2-1) TypedQuery : 반환할 타입을 명확하게 지정하는 방식일 떄 사용. 쿼리 객체의 메소드 실행 결과로 지정한 타입이 반환.
            2-2) Query : 반환할 타입을 명확하게 지정할 수 없을 때 사용. 쿼리 객체 메소드의 실행 결과로 Object[] 이 반환.
        3. 쿼리 객체에서 제공하는 메소드 getSingleResult() 또는 getResultList()를 호출해서 쿼리를 실행하고, 데이터베이스를 조회한다.
            3-1) getSingleResult() : 결과가 정확하 한 행인 경우 사용하며, 없거나 많으면 예외 발생
            3-2) getResultList() : 결과가 2행 이상인 경우 사용, 컬렉션을 반환한다. 없으면 빈 컬렉션 반환.
    *   */


    @Autowired
    private SimpleJPQLRepository repository;


    @DisplayName("TypedQuery 를 이용한 단일메뉴(단일행, 단일컬럼) 조회 테스트")
    @Test
    void testSingleMenuByTypedQuery(){

        // when
        String menuName = repository.selectSingleMenuByTypedQuery();

        Assertions.assertEquals("한우딸기국밥", menuName);
        Assertions.assertEquals(repository.findMenu(8).getMenuName(), menuName);

    }

    @DisplayName("Query를 이용한 단일메뉴(단일행, 단일컬럼) 조회 테스트")   // Query 는 타입이 정해져있지 않을 때 사용하며, Object 가 반환.
    @Test
    void testSingleMenuByQuery(){


        Object menuName = repository.selectSingleMenuByQuery();

        Assertions.assertEquals("한우딸기국밥", menuName);
        Assertions.assertTrue(menuName instanceof String);      //  반환된 타입(menuNam)이 스트링 타입인지 확인

        System.out.println("menuName : " + menuName);

    }


    @DisplayName("TypedQuery 를 이용한 단일행 조회 테스트")
    @Test
    void testSingleRowByTypedQuery() {

        Menu menu = repository.selectSingleRowByTypedQuery();

        Assertions.assertEquals(8, menu.getMenuCode());

        System.out.println("menu : " + menu);
    }


    @DisplayName("TypedQuery 를 이용한 다중행 조회 테스트")
    @Test
    void testMultipleRowByTypedQuery() {

        List<Menu> menuList = repository.selectMultipleRowByTypedQuery();

        Assertions.assertNotNull(menuList);

       menuList.forEach(menu -> System.out.println(menu)); // menuList 에서 메뉴 한 행씩 결과 출력

//        menuList.forEach(System.out::println);  // 위의 식과 동일하며 함축형 식
    }

    @DisplayName("Query 를 이용한 다중행 조회 테스트")
    @Test
    void testMultipleRowByQuery() {


        List<Menu> menuList = repository.selectMultipleRowByQuery();

        Assertions.assertNotNull(menuList);

        menuList.forEach(System.out::println);

    }

    @DisplayName("DISTINCT 를 활용한 중복 제거 다중행 조회 테스트")
    @Test
    void testDistinct(){

        /* tbl_menu 에서 모든 메뉴들은 카테고리를 가지고 있다. 여기에서 중복된 카테고리 코드들은 제거하고 카테고리 코드들만의 리스트를 반환받기 */

        List<Integer> categoryCodeList = repository.selectUseDistinct();
        // 카테고리 코드는 int 형으로 되어있으나, 이를 클래스화시킨 wrapper class 인 Integer 를 사용.
        // int -> Integer, char, short, String

        Assertions.assertNotNull(categoryCodeList);

        categoryCodeList.forEach(System.out::println);


    }



    }
