package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class IdClassCompositeKeyTest {

    @Autowired
    private CartRegistService cartRegistService;

    private static Stream<Arguments> getCartInfo() {
        return Stream.of(
                Arguments.of(1, 2, 10),
                Arguments.of(1, 3, 5),
                Arguments.of(2, 1, 1),
                Arguments.of(2, 2, 20)
        );
    }

    @ParameterizedTest(name = "{0}번 회원이 {1}번 책을 카트에 {2}권 담기 테스트")
    @MethodSource("getCartInfo")
    void testAddItemToCart(int cartOwnerMemberNo, int addedBookNo, int quantity) {

        Assertions.assertDoesNotThrow(
                () -> cartRegistService.addItemToCart(
                        new CartDTO(cartOwnerMemberNo, addedBookNo, quantity)
                )
        );

    }

}

/*
1. private static Stream<Arguments> getCartInfo():

이 메서드는 Stream<Arguments> 타입을 반환합니다. Stream은 Java의 스트림 API를 사용하여 여러 값을 처리할 수 있는 시퀀스입니다.
Arguments는 JUnit 5에서 제공하는 클래스이며, 테스트 메서드에 전달될 여러 파라미터들을 감싸는 역할을 합니다


2. return Stream.of(...):

Stream.of 메서드는 주어진 값들을 포함하는 스트림을 생성합니다. 이 예제에서는 Arguments.of를 사용하여 각각의 인자 세트를 스트림의 요소로 만듭니다.
각 Arguments.of는 각각의 테스트 케이스를 나타내며, Arguments.of(1, 2, 10)은 1, 2, 10이라는 세 개의 파라미터를 포함합니다.


3. Arguments.of(...):

Arguments.of는 주어진 파라미터들을 하나의 Arguments 객체로 만듭니다.
이 예제에서는 네 개의 Arguments 객체가 생성됩니다:
Arguments.of(1, 2, 10): 첫 번째 테스트 케이스의 파라미터(cartOwnerMemberNo=1, addedBookNo=2, quantity=10).
Arguments.of(1, 3, 5): 두 번째 테스트 케이스의 파라미터(cartOwnerMemberNo=1, addedBookNo=3, quantity=5).
Arguments.of(2, 1, 1): 세 번째 테스트 케이스의 파라미터(cartOwnerMemberNo=2, addedBookNo=1, quantity=1).
Arguments.of(2, 2, 20): 네 번째 테스트 케이스의 파라미터(cartOwnerMemberNo=2, addedBookNo=2, quantity=20).

* 요약
getCartInfo 메서드는 네 개의 Arguments 객체를 포함하는 스트림을 반환합니다.
각 Arguments 객체는 테스트 메서드에 전달될 세 개의 파라미터(cartOwnerMemberNo, addedBookNo, quantity)를 포함합니다.
@ParameterizedTest와 @MethodSource 어노테이션을 통해 이 파라미터들이 testAddItemToCart 메서드로 전달되어 각각의 테스트 케이스가 실행됩니다.
이렇게 함으로써, cartRegistService.addItemToCart 메서드는 네 개의 서로 다른 입력 값 세트에 대해 테스트됩니다.
 */