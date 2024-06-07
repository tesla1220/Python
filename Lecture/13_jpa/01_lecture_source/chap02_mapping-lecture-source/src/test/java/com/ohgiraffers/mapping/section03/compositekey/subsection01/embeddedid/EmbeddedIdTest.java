package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class EmbeddedIdTest {

    @Autowired
    private LikeBookService likeBookService;

    // Spring 의 의존성 주입을 통해 LikeBookService 빈을 주입합니다. 이를 통해 테스트 클래스에서 서비스 메소드를 호출할 수 있습니다.


    /* 필기
    *   복합키가 존재하는 테이블의 매핑
    *   1. EmbeddedId
    *       : @Embeddable 클래스에 복합키를 정의하고 엔티티에 @EmbeddedId 를 이용해 복합키 클래스를 매핑한다.
    *       : 복합키의 일부 필드만을 매핑할 수 있으므로, 필드 수가 많은 경우 유연한 매핑이 가능하다.
    *  */


    // 이 메소드는 테스트에 사용할 복합키 값을 스트림 형태로 제공합니다. Stream.of 메소드를 사용하여 다양한 인수 조합을 Arguments 객체로 생성합니다.
    // 이 값들은 테스트 메소드에 파라미터로 전달될 것입니다.
    private static Stream<Arguments> getLikeCompositeKey() {
        return Stream.of(
                Arguments.of(1,1),
                Arguments.of(2,1),
                Arguments.of(1,2),
                Arguments.of(2,2)

        );
    }

    @ParameterizedTest(name = "{0}번 회원이 {1}번 책 좋아요 등록 테스트")
    @MethodSource("getLikeCompositeKey")
    void testGenerateLike(int likedMemberNo, int likedBookNo){

        // testGenerateLike 메소드는 likedMemberNo와 likedBookNo라는 두 개의 정수 인수를 받습니다. 이 인수들은 getLikeCompositeKey 메소드에서 제공된 값들입니다.

        Assertions.assertDoesNotThrow(
                () -> likeBookService.generateLikeBook(
                        new LikeDTO(likedMemberNo, likedBookNo)
                )
        );

    }

    /* @ParameterizedTest 는 여러 다른 인수 세트를 사용하여 동일한 테스트 메소드를 여러 번 실행할 수 있도록 합니다.
    name 속성을 통해 각 테스트 실행의 이름을 지정할 수 있으며, {0}과 {1}은 인수 자리 표시자로 사용됩니다. */

    /* @MethodSource("getLikeCompositeKey")는 매개변수로 사용할 값을 제공하는 메소드의 이름을 지정합니다.
    이 경우, getLikeCompositeKey 메소드가 테스트에 사용될 인수를 제공합니다. */

    /* Assertions.assertDoesNotThrow 는 주어진 람다 표현식이 예외를 던지지 않는지 확인합니다.
         즉, likeBookService.generateLikeBook(new LikeDTO(likedMemberNo, likedBookNo))가 예외를 던지지 않으면 테스트가 통과합니다.
        likeBookService.generateLikeBook(new LikeDTO(likedMemberNo, likedBookNo))는
        likedMemberNo와 likedBookNo를 포함하는 LikeDTO 객체를 생성하여 generateLikeBook 메소드에 전달합니다. */

    /* 요약
     이 매개변수화된 테스트는 LikeBookService 의 generateLikeBook 메소드가 다양한 likedMemberNo와 likedBookNo 조합에 대해 예외를 던지지 않는지 확인합니다.
     각각의 테스트 케이스는 getLikeCompositeKey 에서 제공된 값들을 사용하여 실행됩니다.
     */

}
