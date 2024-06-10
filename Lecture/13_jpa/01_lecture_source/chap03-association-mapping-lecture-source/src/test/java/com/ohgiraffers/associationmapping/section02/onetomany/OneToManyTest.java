package com.ohgiraffers.associationmapping.section02.onetomany;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class OneToManyTest {

    @Autowired
    private OneToManyService oneToManyService;      // 의존성 주입


    @DisplayName("1:N 연관관계 객체그래프 탐색을 이용한 조회 테스트")
    @Test
    void oneToManyFindTest() {


        // given
        int categoryCode = 10;


        // when
        Category category = oneToManyService.findCategory(categoryCode);


        // then
        Assertions.assertNotNull(category);

    }

    /* 필기
        @OneToMany 일 때, 조회임에도 불구하고 @Transactional 애노테이션을 사용하는 이유
        - 1개의 엔티티가 여러 개의 엔티티를 로드해야하는 상황 (ex. Category -> menuList) 일 때는
          여러 개의 엔티티를 로드해야하기 때문에 성능상의 이슈가 발생할 수 있다.
          그래서 Category 엔티티를 조회할 때 Menu 에 대한 엔티티를 바로 로드하지 않고,
          일단 가지고 있다가 진짜 필요할 떄 로드를 하게 된다. (Lazy)
          - 지연로딩이 default 인 경우
            : @OneToMany(fetch = FetchType.LAZY)
            : @ManyToMany
           - 이른로딩이 default 인 경우
            : @ManyToOne(fetch = FetchType.EAGER)       <- 가장 많이 사용되며 권장됨
            : @OneToOne
     */


    private static Stream<Arguments> getInfo(){
        return Stream.of(
                Arguments.of(321,"돈가스 스파게티", 30000, 321, "퓨전분식", "Y")
        );
    }

    @DisplayName("1:N 연관관계 객체 삽입 테스트")
    @ParameterizedTest
    @MethodSource("getInfo")
    void oneToManyInsertTest(int menuCode, String menuName, int menuPrice,
                             int categoryCode, String categoryName, String orderableStatus){

        // given
        CategoryDTO categoryDTO = new CategoryDTO(
                categoryCode, categoryName,null, null

        );

        List<MenuDTO> menuList = new ArrayList<>();

        // MenuDTO 만들기
        MenuDTO menuDTO = new MenuDTO(
                menuCode, menuName, menuPrice, categoryCode, orderableStatus
        );

        // menuList 에 방금 만든 MenuDTO 넣기
        menuList.add(menuDTO);

        // categoryDTO 에 menuList를 우리가 만든 menuList 로 넣기
        categoryDTO.setMenuList(menuList);

        // then : 우리가 만든 categoryDTO 를 담아서 서비스층의 registMenu 로 가져감
        Assertions.assertDoesNotThrow(
                () -> oneToManyService.registMenu(categoryDTO)
        );


    }


}
