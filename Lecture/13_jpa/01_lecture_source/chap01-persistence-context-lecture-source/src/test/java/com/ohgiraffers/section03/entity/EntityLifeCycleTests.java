package com.ohgiraffers.section03.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class EntityLifeCycleTests {

    // 필드 선언
    private EntityLifeCycle lifeCycle;

    @BeforeEach
    void setUp() {
        this.lifeCycle = new EntityLifeCycle();
    }

    @DisplayName("비영속 테스트")  // 컨텍스트에 등록하지 않은 상태
    @ParameterizedTest      // 매개변수 전달
    @ValueSource(ints = {1, 2})
    void testTransition(int menuCode) {     // 파라미터값으로 1과 2를 넘겼으므로 menuCode 공간을 만들어 받을 준비

        Menu foundMenu = lifeCycle.findMenuByMenuCode(menuCode);

        Menu newMenu = new Menu(
                foundMenu.getMenuCode(),
                foundMenu.getMenuName(),
                foundMenu.getMenuPrice(),
                foundMenu.getCategoryCode(),
                foundMenu.getOrderableStatus()
        );

        Assertions.assertNotEquals(foundMenu, newMenu);
        Assertions.assertFalse(lifeCycle.getManagerInstance().contains(newMenu));

    }

    @DisplayName("다른 엔티티 매니저가 관리하는 엔티티의 영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void testOtherManager(int menuCode){

        Menu menu1 = lifeCycle.findMenuByMenuCode(menuCode);
        Menu menu2 = lifeCycle.findMenuByMenuCode(menuCode);

        Assertions.assertNotEquals(menu1, menu2);
    }

    @DisplayName("같은 엔티티 매니저가 관리하는 엔티티의 영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints= {1,2,3})
    void testSameManager(int menuCode){

        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        Menu menu1 = manager.find(Menu.class, menuCode);
        Menu menu2 = manager.find(Menu.class, menuCode);

        Assertions.assertEquals(menu1, menu2);

    }

    @DisplayName("준영속화 detach 테스트")
    @ParameterizedTest
    @CsvSource({"11, 1000", "12, 1000"})    // 메뉴 코드, 메뉴 가격
    void testDetachEntity(int menuCode, int menuPrice) {

        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        // 수정할 예정이므로 트랜잭션 생성
        EntityTransaction transaction = manager.getTransaction();

        // Csv 내용 전달함
        Menu foundMenu = manager.find(Menu.class, menuCode);

        transaction.begin();

        /* detach()
        *   특정 엔티티만 준영속 상태(영속성 컨텍스트가 관리하던 엔티티 객체를 관리하지 않는 상태)로 만든다. */

        manager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice);

        manager.flush();

        Assertions.assertNotEquals(menuPrice, manager.find(Menu.class, menuCode).getMenuPrice());

        transaction.rollback();

    }

    @DisplayName("준영속화 detach 후 다시 영속화 테스트")
    @ParameterizedTest(name = "[{index}] 준영속화된 {0}번 메뉴를 {1}원으로 변경하고 다시 영속화되는지 확인")
    @CsvSource({"11, 1000", "12, 1000"})
    void testDetachAndPersist(int menuCode, int menuPrice) {

        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        EntityTransaction transaction = manager.getTransaction();

        Menu foundMenu = manager.find(Menu.class, menuCode);

        transaction.begin();

        manager.detach(foundMenu);

        foundMenu.setMenuPrice(menuPrice);

        /* 필기 : merge
        *   파라미터로 넘어온 준영속 엔티티 객체의 식별자(@ID) 값으로 1차 캐시에서 엔티티 객체를 조회한다.
        *   만약 1차 캐시에 엔티티가 없으면 데이터베이스에서 엔티티를 조회하고 1차 캐시에 저장한다.
        *   조회한 영속 엔티티 객체에 준영속 상태의 엔티티 객체의 값을 병합한 뒤 영속 엔티티 객체를 반환한다.
        *   혹은 조회할 수 없는 데이터의 경우, 새로 생성해서 병합한다.(save or update) */
        manager.merge(foundMenu);
        manager.flush();

        Assertions.assertEquals(menuPrice, manager.find(Menu.class, menuCode).getMenuPrice());
        transaction.rollback();

    }

    @DisplayName("detach 준영속 후 merge 한 데이터 save 테스트")
    @Test
    void testDetachAndMergeSave() {

        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        EntityTransaction transaction = manager.getTransaction();

        Menu foundMenu = manager.find(Menu.class, 20);  // 20은 식별자

        manager.detach(foundMenu);

        transaction.begin();
        foundMenu.setMenuCode(999);
        foundMenu.setMenuName("닭가슴살샐러드");

        manager.merge(foundMenu);
        manager.flush();

        Assertions.assertEquals("닭가슴살샐러드", manager.find(Menu.class, 999).getMenuName());

    }

    @DisplayName("준영속화 clear 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void testClearPersistenceContext (int menuCode) {

        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        Menu foundMenu = manager.find(Menu.class, menuCode);

        // clear() : 영속성 컨텍스트 초기화 -> 영속성 컨텍스트 내의 모든 엔티티를 준영속화 시킨다.
        manager.clear();        // foundMenu 는 준영속화된 상태

        Menu expectedMenu = manager.find(Menu.class, menuCode);     // expectedMenu 는 준영속화되지 않은 상태
        Assertions.assertNotEquals(expectedMenu, foundMenu);        // 둘이 다름

    }

    @DisplayName("준영속화 close 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void closePersistenceContext(int menuCode){

        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        Menu foundMenu =  manager.find(Menu.class, menuCode);

        // close() : 영속성 컨텍스트를 종료한다 -> 영속성 컨텍스트 내 모든 객체를 준영속화 시킨다.
        manager.close();

//        Menu expectedMenu = manager.find(Menu.class, menuCode);
//        Assertions.assertNotEquals(expectedMenu, foundMenu);
        /* 위에서 close 했으므로 expectedMenu 나 foundMenu를 사용할 수가 없는 상태 */

        Assertions.assertThrows(

                IllegalStateException.class,
                () -> manager.find(Menu.class, menuCode)
        );

    }

    @DisplayName("영속성 엔티티 삭제 remove 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1})
    void testRemoveEntity(int menuCode){

        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();

        Menu foundMenu = manager.find(Menu.class, menuCode);

        transaction.begin();

        /* 필기 : remove()
        *   엔티티를 영속성 컨텍스트 및 데이터베이스에서 삭제한다.
        *   단, 트랜젝션을 제어하지 않으면 데이터베이스에 영구 반영되지는 않는다.
        *   트랜젝션을 commit 혹은 flush 하는 순간 영속성 컨텍스트에서 관리하는 엔티티 객체가 데이터베이스에 반영된다. */

        manager.remove(foundMenu);

        manager.flush();        // flush 를 했기 때문에 데이터베이스 내 값 변경

//        Assertions.assertFalse(manager.contains(foundMenu));
        Menu refoundMenu = manager.find(Menu.class, menuCode);
        Assertions.assertEquals(null, refoundMenu);
        transaction.rollback();


    }


}
