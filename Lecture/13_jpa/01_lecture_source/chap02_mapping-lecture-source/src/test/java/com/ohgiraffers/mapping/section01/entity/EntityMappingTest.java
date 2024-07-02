package com.ohgiraffers.mapping.section01.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@SpringBootTest // Springboot 환경에서 Test 시 사용하는 애노테이션
public class EntityMappingTest {

    @Autowired
    private MemberRegistService memberRegistService;

    private static Stream<Arguments> getMember() {
        return Stream.of(
                Arguments.of(
                        1,
                        "user01",
                        "pass01",
                        "너구리",
                        "010-1234-1234",
                        "서울시 노진구",
                        LocalDateTime.now(),
                        "ROLE_MEMBER",
                        "Y"
                ),
                Arguments.of(
                        2,
                        "user02",
                        "pass03",
                        "너구리3",
                        "010-1234-1230",
                        "서울시 도라에몽",
                        LocalDateTime.now(),
                        "ROLE_ADMIN",
                        "Y"
                )
        );
    }

    /* Stream<Arguments>:
    생성한 각각의 Arguments 객체들을 포함하는 스트림을 반환합니다.
    이 스트림은 테스트 메서드의 @MethodSource 어노테이션을 통해 사용되어, 테스트 메서드에 인자를 제공합니다.(아래 테스트들 참고)
    이렇게 설정된 getMember 메서드를 사용하면, 각 테스트 메서드에 여러 인자를 전달하여 다양한 경우를 테스트할 수 있습니다.*/

    @DisplayName("테이블 생성 테스트")
    @ParameterizedTest
    @MethodSource("getMember")
    void testCreateTable(int memberNo, String memberId, String memberPwd,
                         String memberName, String phone, String address,
                         LocalDateTime enrollDate, MemberRole memberRole, String status) {

        // given
        MemberRegistDTO newMember = new MemberRegistDTO(
                memberId,
                memberPwd,
                memberName,
                phone,
                address,
                enrollDate,
                memberRole,
                status
        );

        // 예외가 발생하지 않는 지 확인
        Assertions.assertDoesNotThrow(
                () -> memberRegistService.registMember(newMember)
        );

    }


    @DisplayName("프로퍼티 접근 테스트")
    @ParameterizedTest
    @MethodSource("getMember")
    void testAccessPropertyint(int memberNo, String memberId, String memberPwd,
                               String memberName, String phone, String address,
                               LocalDateTime enrollDate, MemberRole memberRole, String status) {

        // given
        MemberRegistDTO newMember = new MemberRegistDTO(
                memberId,
                memberPwd,
                memberName,
                phone,
                address,
                enrollDate,
                memberRole,
                status
        );

        // when
        String registName = memberRegistService.registMemberAndFindName(newMember);

        //then
        Assertions.assertEquals(memberName, registName);

    }

}


    /* 추가 필기 : Stream<Arguments>
    Stream<Arguments>는 테스트를 할 때 사용되는 데이터를 간편하게 관리하는 방법 중 하나에요.
    이것은 특히 많은 양의 데이터를 가진 여러 테스트 케이스를 관리할 때 유용해요.
    여러분이 Stream<Arguments>를 사용하면, 각 테스트 케이스에 필요한 데이터를 목록으로 만들어놓을 수 있어요.
    그리고 이 목록에서 테스트를 실행할 때마다 자동으로 데이터가 추출되어 각각의 테스트에 전달됩니다.
     회원 가입 서비스를 테스트한다고 가정해봅시다.
     여러 종류의 회원 데이터가 있을 텐데, 각 회원에 대해 테스트를 진행하려면 많은 양의 테스트 코드를 작성해야 할 것입니다.
     그러나 Stream<Arguments>를 사용하면 테스트 코드를 간단하게 유지하면서도 많은 양의 테스트 데이터를 효과적으로 다룰 수 있습니다.
     각각의 테스트 케이스는 Arguments 객체 안에 있는 데이터를 활용하여 실행됩니다. */

    /* 이 클래스에서 생성자 주입하지 않는 이유 :
    이 테스트 클래스에서는 @Autowired를 사용하여 MemberRegistService를 필드 주입하고 있습니다.
    따라서 별도의 생성자를 작성하여 의존성을 주입할 필요가 없습니다.
    스프링은 @Autowired 어노테이션이 붙은 필드에 해당하는 빈을 자동으로 주입해줍니다.

    주입되는 MemberRegistService 빈은 테스트 메소드에서 사용되므로,
    필드 주입 방식을 통해 해당 빈을 초기화하고 사용할 수 있습니다.
    이러한 방식은 코드의 간결성을 유지하고, 테스트 클래스의 가독성을 높여줍니다.
    따라서 별도의 생성자를 작성하는 것은 필요하지 않습니다. */

/*  @ParameterizedTest : 파라미터화된 테스트
        JUnit 5에서는 파라미터화된 테스트(Parameterized Test)를 지원합니다.
        파라미터화된 테스트를 사용하면 여러 입력 값 세트에 대해 동일한 테스트를 반복할 수 있습니다.
        이를 통해 테스트 코드의 중복을 줄이고 다양한 입력 값에 대해 테스트할 수 있습니다.*/