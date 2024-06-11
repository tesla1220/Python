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

    // menuCode 로 Menu 엔티티 조회하고, Menu 와 연관된 category 엔티티 가져오기
    @DisplayName("Many to One Test")
    @Test
    void manyToOneTest() {

        // given
        int menuCode = 10;

        // when
        Menu theMenu = manyToOneService.findMenu(menuCode);
        // 매니저가 menuCode 로 Menu.class 에서 조회한



    }





}
