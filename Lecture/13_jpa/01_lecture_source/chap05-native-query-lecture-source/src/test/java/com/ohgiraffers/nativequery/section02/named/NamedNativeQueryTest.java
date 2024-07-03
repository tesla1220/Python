package com.ohgiraffers.nativequery.section02.named;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NamedNativeQueryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    @DisplayName("Named Native Query 를 이용한 조회 테스트")
    public void testSelectByNamedNativeQuery() {
        // given
        // when

        Query nativeQuery = entityManager.createNamedQuery("Category.menuCountOfCategory");

        List<Object[]> resultCategoryList = nativeQuery.getResultList();

        // then
        Assertions.assertNotNull(resultCategoryList);   // ()는 리스트의 특정 요소(행 row)를 가져오는 것이고, []는 배열의 특정 요소(열 column)를 가져오는 것입니다.
        Assertions.assertTrue(entityManager.contains(resultCategoryList.get(0)[0])); // get(0)[0] : 첫 번째 행(Object[] 배열)의 첫 번째 열(Object[]의 요소))의 값
        resultCategoryList.forEach(
                row -> {
                    for ( Object col : row ) {
                        System.out.println(col + "/");
                    }
                    System.out.println();
                }
        );

    }


}
