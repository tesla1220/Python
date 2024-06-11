package com.ohgiraffers.jpql.section04.groupfunction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GroupTests {

    /* 필기 : JPQL 의 그룹함수 => COUNT, MAX, MIN, SUM, AVG
    *   우리가 MySQL 에서 사용한 그룹함수와 차이가 없다.
    *   🔴 주의사항
    *       1. 그룹함수의 반환타입은 결과값이 정수이면 Long, 실수이면 Double 로 반환된다.
    *       2. 값이 없는 상태에서 count 를 제외한 그룹 함수는 null 이 되고, count 만 0 이 된다.
    *           따라서 반환값을 담기 위해 선언하는 변수 타입을 기본자료형으로 하면, 조회결과를 언박싱할 때 nullPointerException 이 발생한다.
    *       3. 그룹함수의 반환자료형은 Long or Double 이기 때문에,
    *          Having 절에서 그룹함수 결과값과 비교하기 위한 파라미터 타입은 Long 또는 Double 로 해야한다.
    *   */

    /* 필기
    *   Group By -> 데이터를 특정 컬럼을 기준으로 그룹화한다.
    *   select category_code, count(*) from tbl_menu 카테고리코드를 기준으로 그룹화한다.
    *   Having 절은 Group By 절로 그룹화 된 결과에 대해 조건을 지정 -> where
    *   Having 절은 언제 사용할까? -> 집계함수(그룹화) 에 결과에 대해 조건을 지정할 때 */

    @Autowired
    private GroupRepository repository;

    @DisplayName("특정 카테고리에 등록된 메뉴 수 조회")
    @Test
    void testCountMenuOfCategory() {

        // categoryCode 가 4인 메뉴가 몇 개(행)이 있는 지 확인해보자
        int categoryCode = 4;

        long countOfMenu = repository.countMenuOfCategory(categoryCode);

        System.out.println("countOfMenu : " + countOfMenu);
        Assertions.assertTrue(countOfMenu >= 0);
    }


    @DisplayName("COUNT 를 제외한 다른 그룹 함수의 조회결과가 없는 경우 테스트")
    @Test
    void testWithoutCount() {

        // 데이터베이스에 없는 categoryCode 설정
        int categoryCode = 50;

//        long sumOfPrice = repository.noResult(categoryCode);

//        Assertions.assertTrue(sumOfPrice >= 0 ); => nullPointerException 발생이유 -> 위의 필기 2번 참고


        Assertions.assertDoesNotThrow(
                () -> { Long sumOfPrice = repository.noResult(categoryCode); }
        );
    }

    @DisplayName("GROUP BY 절과 HAVING 절을 사용한 조회 테스트")
    @Test
    void testGroupAndHaving() {

//        int menuPrice = 50000;
        long minPrice = 50000L;

        List<Object[]> sumPriceOfCategoryList = repository.selectGroupAndHaving(minPrice);

        Assertions.assertNotNull(sumPriceOfCategoryList);

        sumPriceOfCategoryList.forEach(
                row -> {
                    for (Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );

    }

}
