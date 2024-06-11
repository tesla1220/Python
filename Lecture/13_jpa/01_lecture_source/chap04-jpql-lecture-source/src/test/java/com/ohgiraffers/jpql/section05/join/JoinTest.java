package com.ohgiraffers.jpql.section05.join;

import jakarta.persistence.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JoinTest {

    /* 필기
     *   1. 일반 조인 : 일반적인 SQL 조인 (inner 조인(교집합), outer(left(가장 많이 사용), right) 조인, 컬렉션 (1대 다) 조인
     *   2. Fetch 조인 : JPQL 에서 성능 최적화를 위해 제공하는 기능.
     *                  연관된 엔티티나 컬렉션을 한 번에 조회.
     *                 -> 지연로딩(Lazy)이 아닌 즉시로딩(Eager)을 수행하며 join fetch 명령어를 이용한다.
     * */

    @Autowired
    private JoinRepository repository;

    @DisplayName("Inner Join 을 이용한 조회 테스트")
    @Test
    void testInnerJoin() {

        List<Menu> menuList = repository.selectInnerJoin();

        Assertions.assertNotNull(menuList);

        menuList.forEach(System.out::println);
    }

    @DisplayName("Outer Join 을 이용한 조회 테스트")
    @Test
    void testOuterJoin() {

        List<Object[]> menuList = repository.selectOuterJoin();

        Assertions.assertNotNull(menuList);

        menuList.forEach(
                row -> {
                    for (Object column : row) {
                        System.out.println(column + " ");
                    }
                    System.out.println();
                }
        );
        /* 결과 => 메뉴 이름 null 존재 / 카테고리 이름은 null 값 없음 */
    }

    @DisplayName("Collection Join 을 이용한 조회 테스트")
    @Test
    void testCollectionJoin() {

        List<Object[]> categoryList = repository.selectCollectionJoin();

        Assertions.assertNotNull(categoryList);
        categoryList.forEach(
                row -> {
                    for(Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );
    }

    @DisplayName("Fetch Join 을 이용한 조회 테스트")
    @Test
    void testFetchJoin() {

        List<Menu> menuList = repository.selectFetchJoin();

        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

}
