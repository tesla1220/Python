package com.ohgiraffers.jpql.section03.paging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PagingTests {

    /*  필기
           페이징 처리용 SQL 문은 DBMS 에 따라 각각 문법이 다른 문제점을 가지고 있다.
           JPA 는 이러한 페이징을 API 를 통해 추상화하여 간단하게 처리할 수 있도록 제공한다.
              🟠JPA 제공
            - setFirstResult(int startPosition) :  조회를 시작할 위치 (0 부터 시작)
            - setMaxResults(int maxResult) : 조회할 데이터의 수
              🟠 SQL 제공
            - limit : 쿼리 결과 최대 행 수를 지정할 수 있다. -> 행의 수 제한
            - offset : 쿼리 결과에서 반환을 시작할 행의 위치
            - limit 과 offset 합침 -> 특정 위치에서 시작해서 지정된 행의 수를 반환
            */

    @Autowired
    private PagingRepository repository;


    @DisplayName("페이징 API 를 이용한 조회 테스트")
    @Test
    void testUsingPagingAPI() {

        // given
        int offset = 10;        // offset 변수 : 조회를 시작할 첫 번째 결과의 위치
        int limit = 5;          // limit 변수 : 반환할 최대 결과 수

        // when
        List<Menu> menuList = repository.usingPagingAPI(offset, limit);

        /* repository.usingPagingAPI(offset, limit) 메서드를 호출하여 Menu 엔티티 목록을 가져옵니다.
          offset과 limit을 사용하여 페이징된 결과를 가져옵니다. 결과는 menuList 변수에 저장됩니다. */

        // then
        Assertions.assertTrue(menuList.size() > 0 && menuList.size() < 6);
        /* 목록의 크기가 0보다 크고 6보다 작은지 확인. 이 검증은 limit이 5로 설정되어 있으므로, 결과가 최소 1개 이상, 최대 5개 이하임을 보장 */

        menuList.forEach(menu -> System.out.println(menu));


    }
}
