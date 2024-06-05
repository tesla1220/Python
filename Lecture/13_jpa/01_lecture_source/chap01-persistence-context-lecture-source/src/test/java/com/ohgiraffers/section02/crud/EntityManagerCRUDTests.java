package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class EntityManagerCRUDTests {

    private EntityManagerCRUD crud;


    // 해당하는 단위 테스트 , 테스트코드가 실행하기 전에 crud 라는 인스턴스가 생성되서 해당 클래스에서 이 인스턴스 사용 가능
    @BeforeEach
    void initManager() {
        this.crud = new EntityManagerCRUD();
    }

    // 테스트 검증이 끝나면 다시 원상복구해야하므로 rollback 메소드 만들음
    @AfterEach
    void rollback() {

        EntityTransaction transaction = crud.getManagerInstance().getTransaction();
        transaction.rollback();     // 하나의 단위 테스트가 끝날 때마다 롤백해줌

    }

    @DisplayName("메뉴 코드로 메뉴 조회 테스트")
    /* 필기
    *   테스트 할 때 여러 값들을 이용해서 검증을 진행해야 하는 경우, 경우의 수만큼 테스트 메소드를 작성해야한다.
    *   "내가 여러 개의 값을 던져줄테니가 네가 테스트를 시행해" 라는 뜻
    *   @ParameterizedTest 어노테이션을 붙이게 되면 테스트 메소드에 매개변수를 선언할 수 있다.
    *   (일반적인 테스트 메소드는 매개변수 사용 불가)
    *   파라미터로 전달할 값의 목록만큼 반복적으로 테스트 메소드를 실행하며, 준비된 값 목록을 전달하여 테스트를 실행할 수 있다. --> given 대체
    * */
    @ParameterizedTest
    /* 필기 : 여러 개의 테스트 전용 파라미터 전달. 쉼표(,) 로 값을 구분할 수 있다. 쉼표로서 매개변수를 두 개를 전달. 쉼표를 기준으로 한 세트로 구분 */
    @CsvSource({"1,1","2,2","3,3"})        // @CsvSource 괄호 안에 전달할 값 넣음.  여러 값을 전달할 때는 스프링부트와 같이 {} 사용
    void testFindMethodByMenuCode(int menuCode, int expected) {

        // when
        Menu foundMenu = crud.findMenuByMenuCode(menuCode);

        // then
        Assertions.assertEquals(expected, foundMenu.getMenuCode());
    }

    //arguments : 전달인자 / 이 글자대로 설정하면 어떤 값이든 받겠다는 뜻
    private static Stream<Arguments> newMenu(){
        // static: 이 메소드는 객체를 만들지 않고도 사용할 수 있어.
        // Stream<Arguments>: 이 메소드는 여러 개의 값을 묶어서 한 번에 처리할 수 있는 Stream 이라는 특별한 도구를 반환해.
        // Arguments 는 그 값들이 어떤 형태인지 알려줘.

        return Stream.of(           // Stream.of(...): 여러 개의 값을 하나로 묶어서 돌려주는 Stream 을 만들겠다
                Arguments.of(       // Arguments.of(...): 여러 개의 값을 한 묶음으로 만드는 방법
                        "신메뉴",
                        20000,
                        4,
                        "Y"
                )
        );
    }

    @DisplayName("새로운 메뉴 추가 테스트")
    @ParameterizedTest
    @MethodSource("newMenu")
    void testRegist(String menuName, int menuPrice, int categoryCode, String orderableStatus){
        // 메뉴 코드 안넣은 이유 : 엔티티에 메뉴코드 auto increment 로 이미 넣어놨기 때문


        //when
        Menu newMenu = new Menu(menuName, menuPrice, categoryCode, orderableStatus);
        // Menu 괄호 내용에 빨간 불 뜨는 이유 :
        // Menu 엔티티 클래스에 해당 argument 에 초기화하는 생성자가 없기 때문 -> Menu 엔티티 클래스에서 menuCode 지운 후 빨간 불 사라짐

        Long count = crud.saveAndReturnAllCount(newMenu);

        // then
        Assertions.assertEquals(22,count);

    }

    @DisplayName("메뉴 이름 수정 테스트")
    @ParameterizedTest
    @CsvSource("1, 변경된 메뉴")

    void testModifyMenuName(int menuCode, String menuName){

        // 괄호 안에 값을 넘겼기 때문에 given 생략

        // when
        Menu modifyMenu = crud.modifyMenuName(menuCode, menuName);

        // then
        // 현재 modifyMenu 변경된 값 담긴 상태 (모든 연산 수행 완료 상태)
        Assertions.assertEquals(menuName, modifyMenu.getMenuName());

    }

}
