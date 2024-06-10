package com.ohgiraffers.associationmapping.section01.manytoone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class ManyToOneAssociationTest {

    /* 수업목표 : 연관관계 Mapping 에 대해 이해해보자
        필기
            Entity 클래스(데이터베이스라고 이해하자) 간의 관계를 매핑하는 것을 의미한다.
            -> 이를 통해 객체를 이용하여 데이터베이스의 테이블 간 관계를 매핑할 수 있다.
            [ 다중성에 의한 분류 ]
            : 연관관계가 있는 객체 관계에서 실제로 연관을 가지는 객체의 수에 따라서 분류된다.(= 더 많은 객체의 수가 선택됨. ex) 1:1 vs N:1 => N:1 이 선택됨)
            - 1 : 1 (OneToOne)
            - 1 : N (OneToMany)
            - N : 1 (ManyToOne)
            - N : N (ManyToMany)
            [ 방향에 따른 분류 ]
            : 테이블의 연관관계는 외래키를 이용한 양방향 연관관계의 특징을 가진다.
            하지만 참조에 의한 객체의 연관관계는 단방향 (ex. Category 테이블에서는 Menu 조회 불가) 이다.
            객체 간의 연관관계를 양방향으로 만들고 싶은 경우, 반대쪽에서도 필드를 추가해서 참조를 보관하면 된다.
            그러나 이는 엄밀하게는 양방향이 아닌 단방향 관계가 2개인 것으로 볼 수 있다.
     */

    /* 필기 : ManyToOne
        ManyToOne 은 다수의 엔티티가 하나의 엔티티를 참조하는 상황에서 사용된다.
        ex) 하나의 카테고리가 여러개의 메뉴를 가질 수 있는 상황에서, 메뉴 엔티티가 카테고리 엔티티를 참조하고 있는 경우
        -> Menu 엔티티는 Many, Category 엔티티는 One 이 된다.
        연관관계를 가지는 엔티티를 조회하는 방법
        1. 객체 그래프 탐색 (객체 연관관계를 사용한 조회) : '참조연산자.'를 통해 접근할 수 있다.
        ex) 메뉴 테이블이 카테고리 엔티티 정보를 가지고 있기 때문에 'menu.category' 를 통해 카테고리로 들어갈 수 있다.
        2. 객체 지행 쿼리(JPQL)
     */

    @Autowired
    private ManyToOneService manyToOneService;


    @DisplayName("N:1 연관관계 객체그래프 탐색을 이용한 조회 테스트")
    @Test
    void manyToOneFindTest() {

        // given
        int menuCode = 10;

        // when : 엔티티 매니저에게 메뉴를 찾아달라고 명령
        Menu foundMenu = manyToOneService.findMenu(menuCode);

        // then : 해당 메뉴에 해당하는 카테고리에 접근
        Category category = foundMenu.getCategory();
        System.out.println("category = " + category);

        Assertions.assertNotNull(category);
        // 먼저, category 라는 이름의 객체가 있는 지 테스트

    }

    @DisplayName("N:1 연관관계 객체지향쿼리(JPQL) 사용 카테고리 이름 조회 테스트")
    @Test
    void manyToOneJPQLFindTest() {
        int menuCode = 10;

        String categoryName = manyToOneService.findCategoryNameByJpql(menuCode);

        System.out.println(categoryName);

        Assertions.assertNotNull(categoryName);
    }

    private static Stream<Arguments>getInfo(){
        return Stream.of(
                Arguments.of(123,"돈가스 스파게티", 30000, 123, "퓨전분식", "Y")
        );
    }

    /* 필기
        commit() 을 할 경우에는 컨텍스트 내에 저장된 영속성 객체를 insert 하는 쿼리문이 동작을 한다.
        단,부모 테이블(tbl_category)에 먼저 값이 들어있어야 그 카테고리를 코드를 참조하는 자식 테이블(tbl_menu)에 데이터를 넣을 수 있다.
        이 때 필요한 것이!!!!!!!!
        @ManyToOne 애노테이션에 영속성 전이 설정을 해주는 것이다.
        영속성 전이란 특정 엔티티를 영속화 할 때 연관된 엔티티도 함께 영속화를 진행한다는 의미다.
        즉, 'cascade = CascadeType.PERSIST' 를 설정하게 되면, menu_and_category 엔티티를 영속화할 때 Category 엔티티도 함꼐 영속화된다.
    *   */

    @DisplayName("N:1 연관관계 객체 삽입 테스트")
    @ParameterizedTest
    @MethodSource("getInfo")
    void manyToOneInsertTest(int menuCode, String menuName, int menuPrice,
                             int categoryCode, String categoryName, String orderableStatus){

        MenuDTO menu = new MenuDTO(
                menuCode,
                menuName,
                menuPrice,
                new CategoryDTO(
                        categoryCode,
                        categoryName,
                        null),
                orderableStatus
                );
        Assertions.assertDoesNotThrow(
                () -> manyToOneService.registMenu(menu)
        );

        /* 이 테스트 코드는 데이터베이스에서 category 가 FK 로 설정되어있기 때문에 category가 존재하지 않으면
        * menu 에 Insert 자체가 불가. 그래서 실제 실행창을 보면 hibernate 가 tbl_category 먼저 insert 한 후 tbl_menu 에 insert 함
        * 요약: 데이터베이스 설계에서 category 테이블이 menu 테이블과 일대다 관계(Many-to-One)를 가지기 때문에 menu가 category를 참조 */
    }
}
