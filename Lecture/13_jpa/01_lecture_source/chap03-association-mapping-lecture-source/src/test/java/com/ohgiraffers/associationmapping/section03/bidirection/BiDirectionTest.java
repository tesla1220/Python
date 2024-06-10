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
    @DisplayName("양방향 연관관계 주인 객체를 이용한 Insert 테스트")
    @ParameterizedTest
    @MethodSource("getMenuInfo") // 위에서 만든 메소드 사용
    void ownerInsertTest1(int menuCode, String menuName, int menuPrice, String orderableStatus){

        Category category = biDirectionService.findCategory(4);
        Menu menu = new Menu(menuCode, menuName, menuPrice, category, orderableStatus);  // category 는 위의 category 정보로 넣어줌

        Assertions.assertDoesNotThrow(
                () -> biDirectionService.registMenu(menu)
        );

    }

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
