package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryGenerator {

    // singleton 으로 만들기
    private static EntityManagerFactory factory
            = Persistence.createEntityManagerFactory("jpatest");

    /*
    EntityManagerFactory는 JPA(Java Persistence API)의 중요한 구성 요소로,
        엔티티 매니저(EntityManager)를 생성하는 팩토리입니다.
        이 변수는 static으로 선언되어 있어 클래스 로드 시점에 한 번만 생성됩니다.
        이는 EntityManagerFactory 타입의 factory 변수가 클래스 로드 시점에 한 번만 생성된다는 것을 의미합니다.
        private로 선언되어 있어 외부에서 직접 접근할 수 없습니다.
        이는 외부에서 인스턴스를 생성하거나 수정하지 못하도록 막기 위함입니다.

     * Persistence.createEntityManagerFactory("jpatest")는 JPA에서 제공하는 정적 메서드로,
     * 주어진 이름("jpatest")을 사용하여 EntityManagerFactory를 생성합니다.
     * "jpatest"는 persistence.xml 파일에 정의된 영속성 유닛(Persistence Unit) 이름입니다.

         **퍼시스턴스(Persistence)**는 "지속성"이라고 할 수 있어.
         쉽게 말하면, 우리가 컴퓨터에 어떤 정보를 저장하고, 그 정보를 나중에 다시 사용할 수 있게 하는 것을 말해.
         Persistence 는 중요한 정보를 저장해서 나중에 다시 사용할 수 있도록 하는 거야.
         컴퓨터에서는 프로그램이 종료되더라도 정보가 사라지지 않고 그대로 남아있도록 도와주는 역할을 하지.
     * */


    // 이 클래스 외부에서 접근 못하도록 아래와 같이 설정
    private EntityManagerFactoryGenerator() {}

    /* 생성자를 private 으로 선언하여 외부에서 이 클래스를 인스턴스화할 수 없게 합니다.
    이는 싱글톤 패턴의 중요한 부분으로, 클래스의 인스턴스가 오직 하나만 존재하도록 보장합니다.
    * */


    // 상기 메소드들은 다 private 로 외부에서 접근 불가하기 때문에 외부 접근 가능하도록 아래 메소드 만들어줌
    public static EntityManagerFactory getInstance() {
        /* 이 메서드는 클래스의 유일한 인스턴스를 반환합니다. static 메서드이기 때문에 클래스 이름으로 직접 호출할 수 있습니다. */

        // getInstance 를 통해서만 접근 가능하도록 설정
        return factory;
        /* 클래스 로드 시 생성된 factory 인스턴스를 반환합니다.
        이를 통해 어디서든 이 메서드를 호출하여 동일한 EntityManagerFactory 인스턴스를 사용할 수 있습니다. */

    }

    /* 요약
    싱글톤 패턴 구현: 이 코드는 싱글톤 패턴을 사용하여 EntityManagerFactory 의 인스턴스를 하나만 생성하고, 어디서든 동일한 인스턴스를 사용할 수 있도록 합니다.
    효율적인 리소스 사용: EntityManagerFactory 는 리소스 집약적인 객체이기 때문에 한 번만 생성하고 여러 곳에서 재사용하는 것이 효율적입니다.
    안전한 접근 제어: private 생성자와 private static 변수로 외부에서의 불필요한 인스턴스 생성을 방지합니다.
*/
}
