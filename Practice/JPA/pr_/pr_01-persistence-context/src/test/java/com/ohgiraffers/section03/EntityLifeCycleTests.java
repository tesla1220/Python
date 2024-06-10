package com.ohgiraffers.section03;

import com.ohgiraffers.section03.entity.EntityLifeCycle;
import com.ohgiraffers.section03.entity.EntityManagerGenerator;
import com.ohgiraffers.section03.entity.Menu;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class EntityLifeCycleTests {

    private EntityLifeCycle lifeCycle;

    @BeforeEach
    void setUp() {
        this.lifeCycle = new EntityLifeCycle();
    }

    /* this 키워드 : 현재 객체의 참조를 의미합니다. 여기서는 this.lifeCycle이 현재 객체의 lifeCycle 필드를 의미합니다.
    setUp() 메서드 : 새로운 EntityLifeCycle 객체를 생성하고 이를 lifeCycle 필드에 할당합니다.
    이로 인해 각 테스트 메서드가 실행될 때마다 lifeCycle 필드가 새로운 EntityLifeCycle 객체를 참조하게 됩니다.*/

    @DisplayName("비영속 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void testTransition(int menuCode){

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
    @ValueSource(ints = {1, 2, 3})
    void testOtherManager(int menuCode){

        Menu menu1 = lifeCycle.findMenuByMenuCode(menuCode);
        Menu menu2 = lifeCycle.findMenuByMenuCode(menuCode);

        Assertions.assertEquals(menu1, menu2);

    }

    @DisplayName("같은 엔티티 매니저가 관리하는 엔티티의 영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testSameManager(int menuCode) {

        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        Menu menu1 = manager.find(Menu.class, menuCode);
        Menu menu2 = manager.find(Menu.class, menuCode);

        Assertions.assertEquals(menu1, menu2);
    }

    @DisplayName("준영속화 detach 테스트")
    @ParameterizedTest
    @CsvSource({"11, 1000", "12, 1000"})
    void testDetachEntity(int menuCode, int menuPrice){

        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        EntityTransaction transaction = manager.getTransaction();

        Menu foundMenu = manager.find(Menu.class, menuCode);

        transaction.begin();

        manager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice);

        manager.flush();

        Assertions.assertNotEquals(menuPrice, manager.find(Menu.class, menuCode).getMenuPrice());

        transaction.rollback();
    }


}
