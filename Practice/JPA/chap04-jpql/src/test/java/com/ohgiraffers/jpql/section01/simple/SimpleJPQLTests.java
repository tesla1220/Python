package com.ohgiraffers.jpql.section01.simple;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SimpleJPQLTests {

    @Autowired
    private SimpleJPQLRepository repository;

    @DisplayName(" TypedQuery 를 이용한 단일메뉴(단일행, 단일컬럼)이름 조회 테스트")
    @Test
    void testSingleMenuByTypedQuery() {

        // when
        String menuName = repository.selectSingleMenuByTypedQuery();

        Assertions.assertEquals("한우딸기국밥", menuName);

        Assertions.assertEquals(repository.findMenu(8).getMenuName(), menuName);

    }

    @DisplayName(" Query 를 이용한 단일메뉴 조회 테스트 ")
    @Test
    void singleMenuByQuery() {

        Object menuName = repository.selectSingleMenuByQuery();

        Assertions.assertEquals("한우딸기국밥", menuName);

        Assertions.assertTrue(menuName instanceof  String);
    }


    @DisplayName(" TypedQuery 를 이용한 단일행 조회 테스트 ")
    @Test
    void testSingleRowByTypedQuery() {

        Menu menu = repository.selectSingleRowByTypedQuery();

        System.out.println("menu : " + menu);
    }

    @DisplayName(" TypedQuery 를 이용한 여러 행 조회 테스트 ")
    @Test
    void testMultiRowByTypedQuery() {

        List<Menu> menuList = repository.multiRowByTypedQuery();

        Assertions.assertNotNull(menuList);

        menuList.forEach(menu -> System.out.println(menu));




    }



}
