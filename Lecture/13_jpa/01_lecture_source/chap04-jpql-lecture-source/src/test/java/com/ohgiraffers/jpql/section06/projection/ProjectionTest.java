package com.ohgiraffers.jpql.section06.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProjectionTest {

    /* 필기 :[ 프로젝션(Projection)]
     *   select 절 조회할 대상을 지정하는 것을 프로젝션이라고 한다.
     *   (SELECT {프로젝션 대상} FROM ...) */

    /* 필기 : 프로젝션 대상의 4가지 방식
            1. 엔티티 프로젝션 (ex. SELECT m FROM section06Menu m)
                - 원하는 객체를 바로 조회할 수 있다.
                - 조회된 엔티티는 영속성 컨텍스트가 관리한다.
            2. 임베디드 타입 프로젝션
                - @Embeddable -> 엔티티와 거의 비슷하게 사용되나, 엔티티가 아니기 때문에 조회의 시작점이 될 수 없다.(FROM 절에 사용 불가)
                - Embedded type : Persistence Context 에서 관리하지 않는다.
            3. 스칼라 타입 프로젝션
                - 숫자, 문자, 날짜 등등의 기본 데이터 타입을 의미한다.
                - 스칼라 타입도 Persistence Context 에서 관리되지 않는다.
            4. new 명령어를 활용한 프로젝션
                - 다양한 종류의 단순값들을 단순 DTO 조회하는 방식
                - 'new 패키지명.DTO명' 을 쓰게 되면 해당하는 DTO 로 바로 값을 반환받을 수 있다.
                - 클래스 객체는 엔티티가 아니기 때문에 Persistence Context 에서 관리되지 않는다.
    *   */

    @Autowired
    private ProjectionService service;

    /* 1. 엔티티 프로젝션 */
    @DisplayName("단일 엔티티 프로젝션 테스트")
    @org.junit.jupiter.api.Test
    void testSingleEntityProjection(){

        List<Menu> menuList = service.singleEntityProjection();

        Assertions.assertNotNull(menuList);
    }


    @DisplayName("양방향 연관관계 엔티티 프로젝션 테스트")
    @org.junit.jupiter.api.Test
    void testBiDirectionEntity() {

        int menuCode = 7;

        BiDirectionCategory categoryOfMenu = service.biDirectionProjection(menuCode);

        Assertions.assertNotNull(categoryOfMenu);

    }

    @Autowired
    private ProjectionRepository repository;

    @DisplayName("Embedded Type Projection ProjectionTest")
    @Test
    void testEmbeddedProjection() {
        /* 우리가 조회를 할건데, menuName 과 menuPrice 만 조회할 거임 */
        List<MenuInfo> menuInfoList = repository.embeddedProjection();

        Assertions.assertNotNull(menuInfoList);
        menuInfoList.forEach(System.out::println);
    }

    @DisplayName("TypedQuery 를 이용한 스칼라 타입 프로젝션 테스트")
    @Test
    void testScalarTypedQuery() {

        List<String> categoryNameList = repository.scalarTypedQuery();

        Assertions.assertNotNull(categoryNameList);

        categoryNameList.forEach(System.out::println);
    }

    @DisplayName("Query 를 이용한 스칼라 타입 프로젝션 테스트")
    @Test
    void testScalarQuery() {

        /* 카테고리 코드랑, 카테고리 이름 조회 */
        List<Object[]> categoryList = repository.scalarQuery();

        Assertions.assertNotNull(categoryList);

        categoryList.forEach(
                row -> {
                    for(Object colum : row ) {
                        System.out.print(colum + " ");
                    }
                    System.out.println();
                }
        );

    }

    @DisplayName("new 명령어를 이용한 프로젝션 테스트")
    @Test
    void testNewProjection() {

        List<CategoryInfoDTO> categoryInfoDTOList = repository.newProjection();

        Assertions.assertNotNull(categoryInfoDTOList);

        categoryInfoDTOList.forEach(System.out::println);
    }

}
