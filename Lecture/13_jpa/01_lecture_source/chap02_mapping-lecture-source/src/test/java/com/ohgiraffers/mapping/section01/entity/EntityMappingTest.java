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

    // 기초 질문
    // public EntityMappingTest(MemberRegistService memberRegistService)
    // {this.memberRegistService = memberRegistService)

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