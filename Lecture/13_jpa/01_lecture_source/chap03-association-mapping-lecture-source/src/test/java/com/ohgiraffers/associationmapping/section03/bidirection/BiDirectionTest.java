package com.ohgiraffers.associationmapping.section03.bidirection;

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
public class BiDirectionTest {

    /* 필기
    *   데이터베이스의 테이블은 외래키 하나로 양방향 조회가 가능하지만
    *   객체는 서로 다른 두 단방향 참조를 합쳐서 양방향이라고 한다.
    *   따라서 두 개의 연관관계 중 연관관계의 주인을 정하고, 주인이 아닌 연관관계를 추가하는 방식으로 작성한다.
    *   - 그럼 다 양방향으로 설정하면 되지 않을까? NOPE!
    *       -> 양방향 연관관계는 항상 설정하는 것이 아닌, 반대방향으로도 접근해 객체그래프(참조) 할 일이 많을 때만 사용(설계)한다.
    *   - 양방향 연관관계 매핑 시 연관관계의 주인 설정하는 법
    *       -> 연관관계의 주인은 외래키를 가지고 있는 엔티티가 되어야 한다. (하나의 객체에서 하나의 객체를 어떤 식으로 찾아갈 수 있느냐에 따라)
    *          ex) Menu 가 주인 ( FK가 메뉴에 있기 때문 ) */


    @Autowired
    private BiDirectionService biDirectionService;


    // 양방향으로 설정했을 때도 조회가 잘 되는 지 테스트
    @DisplayName("양방향 연관관계 매핑 조회 테스트1 (연관관계의 주인인 객체)")
    @Test
    void ownerFindTest1() {

        // given ( 현재 연관관계 주인은 Menu )
        int menuCode = 10;

        // when : 처음부터 조회 시 조인한 결과를 가져온다.
        Menu foundMenu = biDirectionService.findMenu(menuCode);

        // then
        Assertions.assertEquals(menuCode, foundMenu.getMenuCode());
        System.out.println("foundMenu = " + foundMenu);

        /* 위 식 실행 결과 쿼리문
        : from
        tbl_menu m1_0
        left join
        tbl_category c1_0
        on c1_0.category_code=m1_0.category_code
        즉, 두 개의 엔티티 정보를 인지하고 있음 */

    }

    @DisplayName("양방향 연관관계 매핑 조회 테스트2 (연관관계의 주인이 아닌 객체)")
    @Test
    void notOwnerFindTest2() {

        // given
        int categoryCode = 10;

        // when
        /* 해당 엔티티를 조회하고 필요 시 연관관계 엔티티를 조회하는 쿼리를 실행한다. */
        Category foundcategory = biDirectionService.findCategory(categoryCode);

        // then
        Assertions.assertEquals(categoryCode, foundcategory.getCategoryCode());
    }




    private static Stream<Arguments> getMenuInfo(){

        return Stream.of(
                Arguments.of(111, "스테이크", 9800, "Y")
        );

    }
    /* 테스트 데이터 제공 메서드 (getMenuInfo): Stream<Arguments>를 반환하여 파라미터화된 테스트에 필요한 데이터를 제공합니다. 여기서는 단일 메뉴 데이터를 제공하고 있습니다. */

    @DisplayName("양방향 연관관계 주인 객체를 이용한 Insert 테스트")
    @ParameterizedTest
    @MethodSource("getMenuInfo")
    // 테스트 메서드 (ownerInsertTest1): @ParameterizedTest와 @MethodSource("getMenuInfo")를 통해 getMenuInfo 메서드에서 제공된 데이터를 사용하여 테스트를 수행합니다.
    void ownerInsertTest1(int menuCode, String menuName, int menuPrice, String orderableStatus){

        // Category 객체 조회: biDirectionService.findCategory(4)를 호출하여 ID가 4인 Category 객체를 조회
        Category category = biDirectionService.findCategory(4);

        // Menu 객체 생성: 주어진 파라미터와 조회한 Category 객체를 사용하여 Menu 객체를 생성
        Menu menu = new Menu(menuCode, menuName, menuPrice, category, orderableStatus);  // category 는 위의 category 넣어줌

        // 서비스 호출 및 예외 검사: biDirectionService.registMenu(menu) 호출 시 예외가 발생하지 않음을 확인
        // 이 테스트는 메뉴와 카테고리 간의 양방향 연관 관계에서 주인 객체를 이용하여 Menu 객체가 올바르게 삽입되는지 검증
        Assertions.assertDoesNotThrow(
                () -> biDirectionService.registMenu(menu)
        );

    }

    /* 요약
    *   이 코드의 목적은 biDirectionService.registMenu 메서드가 Menu 객체와 그에 연관된 Category 객체를 정상적으로 처리하는지 확인하는 것입니다.
    *   예외가 발생하지 않는다면, 테스트는 성공으로 간주됩니다.*/



    private static Stream<Arguments> getCategoryInfo() {
        return Stream.of(
                Arguments.of(111, "양방향 카테고리", null)
        );
    }

    @DisplayName("양방향 연관관계 주인이 아닌 객체를 이용한 삽입 테스트")
    @ParameterizedTest
    @MethodSource("getCategoryInfo")
    void notOwnerInsertTest2(int categoryCode, String categoryName, Integer refCategoryCode){

        Category category = new Category(
                categoryCode,
                categoryName,
                refCategoryCode
        );
        // Category 엔티티에 생성자 다시 만들어 초기화함

        biDirectionService.registCategory(category);

        // 등록 후 조회 성공여부 테스트
        Category foundCategory = biDirectionService.findCategory(categoryCode);

        Assertions.assertNotNull(foundCategory);

    }

}
