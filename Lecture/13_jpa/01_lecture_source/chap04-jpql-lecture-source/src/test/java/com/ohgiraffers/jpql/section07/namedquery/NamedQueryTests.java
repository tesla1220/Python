package com.ohgiraffers.jpql.section07.namedquery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NamedQueryTests {


    /* 필기
        1. 동적 쿼리
            현재 우리가 사용하는 방식처럼 EntityManager 가 제공하는 메소드를 이용해 JPQL 을 문자열로 런타임 시점에 동적으로 쿼리를 만드는 방식
            동적으로 만들어질 쿼리를 위한 조건식이나 반복문을 자바코드로 만들어 이용할 수 있다.
        2. 정적 쿼리
            미리 쿼리문을 정의한 뒤 변경하지 않고 사용하는 쿼리
            미리 정의한 코드는 이름을 보여해서 사용한다. -> NamedQuery

    *   */

    @Autowired
    private NamedQueryRepository repository;

    @DisplayName("동적 쿼리를 이용한 조회 테스트")
    @Test
    void testSelectByDynamicQuery() {

        // given : 우리가 아래 정보로 검색을 할거임
        String searchName = "열무김치라떼";
        int searchCode = 8;

        // when
        List<Menu> menuList = repository.selectByDynamicQuery(searchName, searchCode);

        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);

    }


    @DisplayName("NamedQuery 를 이용한 조회 테스트")
    @Test
    void testNamedQuery() {

        List<Menu> menuList = repository.selectByNamedQuery();

        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }



}
