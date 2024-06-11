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
public class ManyToOneTest {

    @Autowired
    private ManyToOneService manyToOneService;

    @DisplayName("Many to One Test")
    @Test
    void manyToOneTest() {

        int menuCode = 10;

        Menu foundMenu = manyToOneService.findMenu(menuCode);

        // 여기까지 foundMenu 에 menuCode 로 조회한 메뉴 정보 담겨있는상황
        // 해당 메뉴 정보에 해당하는 카테고리에 접근 ⬇️⬇️
        Category category = foundMenu.getCategory();
        System.out.println("category = " + category);

        Assertions.assertNotNull(category);

    }

    @DisplayName("Many To One 연관관계 객체지향쿼리 사용 카테고리 이름 조회 테스트")
    @Test
    void manyToOneJPQLTest(){

        int menuCode = 10;

        String categoryName = manyToOneService.findCategoryNameByJOQL(menuCode);

        System.out.println("categoryName wassup babe : " + categoryName);

        Assertions.assertNotNull(categoryName);

    }

    private static Stream<Arguments> getInfo() {
        return Stream.of(
                Arguments.of(22, "돈가스 스파게티", 30000, 33, "맛있겠당", "Y")
        );
    }

    @DisplayName(" Many To One 연관관계 객체 삽입 테스트")
    @ParameterizedTest
    @MethodSource("getInfo")
    void manyToOneInsertTest(int menuCode, String menuName, int menuPrice,
                             int categoryCode, String categoryName, String orderableStatus){

        MenuDTO menuDTO = new MenuDTO(
                menuCode,
                menuName,
                menuPrice,
                new CategoryDTO(
                        categoryCode,
                        categoryName,
                        12),
                orderableStatus
                );

        Assertions.assertDoesNotThrow(
                () -> manyToOneService.registMenu(menuDTO)
        );
    }


}
