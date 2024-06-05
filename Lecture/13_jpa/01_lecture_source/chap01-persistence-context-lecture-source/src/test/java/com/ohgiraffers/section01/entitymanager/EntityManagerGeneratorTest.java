package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

public class EntityManagerGeneratorTest {

    /* All -> before : 최초 한 번 테스트 코드가 실행하기 전 동작 */

    @BeforeAll // 모든 테스트 코드 동작하기 전 딱 한 번만 실행됨
    static void beforeAllTest(){
        System.out.println("========BeforeAll======");
    }

    @BeforeEach
    void beforeEachTest(){
        System.out.println("========BeforeEach======");
    }

    @AfterEach
    void afterEachTest(){
        System.out.println("========AfterEach======");
    }

    @AfterAll
    static void afterAllTest(){
        System.out.println("========AfterAll======");
    }

    /* 수업목표. Persistence Context 를 이해하기 위한 엔티티 매니저와 팩토리 */

    /* 필기
    *   엔티티 매니저 팩토리
    *    엔티티 매니저를 생성할 수 있는 기능을 제공하는 팩토리 클래스이다.
    *    thread-safe 하므로 여러 쓰레드가 동시에 접근(여러 사람이 한꺼번에 접속)해도 안전하기 때문에 공유해서 재사용 한다.
    *    thread-safe 한 기능들은 매번 생성하기에는 비용(시간, 메모리) 부담이 크기 때문에
    *    application 스코프와 동일하게 singleton 으로 생성해서 관리하는 것이 효율적이다.
    *    따라서 데이터베이스를 사용하는 어플리케이션 당 한 개의 EntityManagerFactory 를 생성한다.
    *
    *
    *   필기 추가
    *    엔티티 매니저 하나를 생성할 때마다 하나의 Persistence Context가 생성돼.
         두 개의 엔티티 매니저가 있다면, 두 개의 독립적인 Persistence Context가 존재해.
         각 Persistence Context는 자신만의 엔티티 상태를 관리하고 변경을 추적해.
         *
         * 독립성: 각 Persistence Context는 독립적으로 동작해.
         * 한 엔티티 매니저의 변경 사항은 다른 엔티티 매니저에 영향을 미치지 않아.
         *
         * 동일성 보장: 같은 Persistence Context 내에서는 같은 엔티티를 여러 번 조회해도 항상 동일한 객체를 반환하지만,
         * 다른 Persistence Context에서는 같은 엔티티라도 별개의 객체로 취급될 수 있어.
* */

    @Test
    @DisplayName("엔티티 매니저 팩토리 생성 확인하기")
    void testCreateFactory(){

        EntityManagerFactory factory = EntityManagerFactoryGenerator.getInstance();

        System.out.println("엔티티 매니저 팩토리 hashcode : " + factory.hashCode());

        Assertions.assertNotNull(factory);

        /* EntityManagerFactoryGenerator를 통해 EntityManagerFactory 객체를 생성하고, 그 객체가 null이 아님을 확인 */

    }

    @Test
    @DisplayName("엔티티 매니저 팩토리 싱글톤 인스턴스 확인")
    void testFactoryIsSingleton(){

        // given
        EntityManagerFactory factory1 = EntityManagerFactoryGenerator.getInstance();

        // when
        EntityManagerFactory factory2 = EntityManagerFactoryGenerator.getInstance();

        // then
        System.out.println("factory1 해시코드 : " + factory1.hashCode());
        System.out.println("factory2 해시코드 : " + factory2.hashCode());

        Assertions.assertEquals(factory1, factory2);

        // 해시코드가 같다 = 같음 = 하나의 공장에서 나옴

    }

    /* 필기.
    *   엔티티 매니저
    *    엔티티를 저장하는 메모리 상의 데이터베이스를 관리하는 인스턴스.
    *    엔티티를 저장, 수정, 삭제, 조회하는 등의 엔티티와 관련된 모든 일을 한다.
    *    팩토리와 달리 thread-safe 하지 않기 때문에 동시성 문제가 발생할 수 있다.
    *    따라서 web 의 경우 일반적으로 request-scope(하나의 트랜젝션이 실행되면 끝남. 부를 때마다 하나씩 생성해서 관리)과 일치시킨다.
    * */

    @Test
    @DisplayName("엔티티 매니저 생성 확인")
    void testCreateManager() {

        EntityManager manager = EntityManagerGenerator.getInstance();

        System.out.println("manager 의 해시코드 : " + manager.hashCode());

        Assertions.assertNotNull(manager);

    }

    @Test
    @DisplayName("엔티티 매니저 스코프 확인")
    void testManagerLifeCycle() {

        // given
        EntityManager manager1 = EntityManagerGenerator.getInstance();


        // when
        EntityManager manager2 = EntityManagerGenerator.getInstance();


        // then
        System.out.println("manager1.hashCode() : " + manager1.hashCode());
        System.out.println("manager2.hashCode() : " + manager2.hashCode());


        Assertions.assertNotEquals(manager1, manager2);
    }

}
