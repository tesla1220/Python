package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
public class EntityManagerFactoryGenerator {

    //  EntityManagerFactory 타입의 static 변수를 선언하고 초기화
    private static EntityManagerFactory factory
                /* EntityManagerFactory 타입의 static 변수를 선언 :
                EntityManagerFactory 클래스의 유일한 인스턴스를 저장할 factory 라는 이름의 변수를 선언.
                단지 EntityManagerFactory 타입의 인스턴스 factory 를 저장할 준비가 된 상태야. 실제로 인스턴스를 생성하려면 생성자를 호출해야 해 */

            = Persistence.createEntityManagerFactory("jpatest");
            /*  EntityManagerFactory 타입의 static 변수를 초기화
            -> Persistence 클래스의 createEntityManagerFactory 메소드를 호출해서 EntityManagerFactory 인스턴스를 생성해.
                이 메소드에 "jpatest" 라는 문자열을 전달하는데,
                이 문자열은 persistence.xml 파일에 정의된 영속성 유닛(persistence unit)의 이름이야.

            ->  Persistence.createEntityManagerFactory("jpatest") 메서드를 사용하여
                persistence.xml 파일에 정의된 persistence-unit 이름이 "jpatest"인 설정을 기반으로 팩토리를 생성합니다.*/


    /*
    EntityManagerFactory 는 JPA(Java Persistence API)의 중요한 구성 요소로,
        엔티티 매니저(EntityManager)를 생성하는 팩토리입니다.
        이 변수는 static 으로 선언되어 있어 클래스 로드 시점에 한 번만 생성됩니다.
        이는 EntityManagerFactory 타입의 factory 변수가 클래스 로드 시점에 한 번만 생성된다는 것을 의미합니다.
        private 로 선언되어 있어 외부에서 직접 접근할 수 없습니다.
        이는 외부에서 인스턴스를 생성하거나 수정하지 못하도록 막기 위함입니다.

         **퍼시스턴스(Persistence)**는 "지속성"이라고 할 수 있어.
         쉽게 말하면, 우리가 컴퓨터에 어떤 정보를 저장하고, 그 정보를 나중에 다시 사용할 수 있게 하는 것을 말해.
         Persistence 는 중요한 정보를 저장해서 나중에 다시 사용할 수 있도록 하는 거야.
         컴퓨터에서는 프로그램이 종료되더라도 정보가 사라지지 않고 그대로 남아있도록 도와주는 역할을 하지.
     * */


    // 생성자 private 으로 외부에서 객체 생성을 막음. 왜 생성자를 private 로 만드는 지는 하단 필기 참고
    private EntityManagerFactoryGenerator() {}
    /*
    EntityManagerFactoryGenerator 클래스의 생성자를 정의한 거야.
    private 으로 선언되어 있기 때문에 외부에서 이 생성자를 직접 호출할 수 없어.
    즉, 외부에서 new EntityManagerFactoryGenerator()으로 새로운 인스턴스를 만들 수 없도록 막는 거야.
    이렇게 하면 EntityManagerFactoryGenerator 클래스 내부에서만 인스턴스를 생성할 수 있어.
    */

    // 싱글톤 인스턴스를 반환하는 public static 메서드
    public static EntityManagerFactory getInstance() {
        /* 이 클래스의 유일한 정적 메서드로, EntityManagerFactory 인스턴스를 반환합니다. 이를 통해 factory 객체에 접근할 수 있습니다. */

        // getInstance 를 통해서만 접근 가능하도록 설정
        return factory;
        /* 클래스 로드 시 생성된 factory 인스턴스를 반환합니다.
        이를 통해 어디서든 이 메서드를 호출하여 동일한 EntityManagerFactory 인스턴스를 사용할 수 있습니다.

       */

    }

    /* 요약
    싱글톤 패턴 구현: 이 코드는 싱글톤 패턴을 사용하여 EntityManagerFactory 의 인스턴스를 하나만 생성하고, 어디서든 동일한 인스턴스를 사용할 수 있도록 합니다.
    효율적인 리소스 사용: EntityManagerFactory 는 리소스 집약적인 객체이기 때문에 한 번만 생성하고 여러 곳에서 재사용하는 것이 효율적입니다.
    안전한 접근 제어: private 생성자와 private static 변수로 외부에서의 불필요한 인스턴스 생성을 방지합니다.
*/

    /*
        * 왜 생성자를 private 으로 만들까?
        1. 유틸리티 클래스: 유틸리티 클래스는 인스턴스 변수가 없고, 모든 메소드가 static 인 경우가 많아.
            이 클래스는 객체를 생성할 필요 없이, 클래스 자체에서 메소드에 접근할 수 있도록 설계돼.
            EntityManagerGenerator 가 바로 그런 유틸리티 클래스의 예시야.

        2. 싱글톤 패턴: 싱글톤 패턴은 특정 클래스의 인스턴스를 하나만 만들고, 모든 코드에서 그 인스턴스를 공유하도록 보장하는 디자인 패턴이야.
            싱글톤 패턴을 구현할 때 생성자를 private 으로 만들어 외부에서 새로운 객체를 생성하지 못하도록 막아.

    * */

    /* 싱글톤 패턴

    싱글톤 패턴(Singleton Pattern)은 클래스의 인스턴스를 하나만 생성하여 전역적으로 접근할 수 있도록 하는 디자인 패턴입니다.
    싱글톤 패턴을 사용하는 이유는 다음과 같습니다:

    자원 절약:
    시스템에서 하나의 인스턴스만 필요한 경우 불필요하게 여러 개의 인스턴스를 생성하지 않으므로 메모리와 같은 시스템 자원을 절약할 수 있습니다.
    예를 들어, 데이터베이스 연결, 설정 파일 로딩, 로그 관리 등은 여러 인스턴스를 생성할 필요가 없으므로 싱글톤 패턴을 사용하여 하나의 인스턴스만 사용하게 할 수 있습니다.

    일관된 접근 제공:
    특정 클래스의 인스턴스가 전역적으로 접근 가능하므로 일관된 방식으로 접근할 수 있습니다. 이는 특히 설정 정보나 공통 라이브러리에 접근할 때 유용합니다.

    인스턴스 생성을 제어:
    클래스의 인스턴스 생성 과정을 제어할 수 있습니다. 생성자를 private으로 선언하고, 정적 메소드를 통해 인스턴스를 반환하도록 하면, 클래스 외부에서 임의로 인스턴스를 생성할 수 없게 됩니다.

    상태 공유:
    싱글톤 인스턴스는 전역 상태를 공유할 수 있습니다. 예를 들어, 설정 정보나 캐시 데이터와 같은 상태를 애플리케이션 전반에서 공유할 수 있습니다.

    성능 향상:
    인스턴스 생성 비용이 높은 객체의 경우 싱글톤 패턴을 사용하면 성능을 향상시킬 수 있습니다. 매번 새로운 인스턴스를 생성하는 대신, 미리 생성된 인스턴스를 재사용하면 성능이 개선됩니다.

    글로벌 포인트 제공:
    애플리케이션 전역에서 접근할 수 있는 단일 접점(global point)을 제공하므로, 특정 클래스의 인스턴스를 추적하고 관리하기 쉬워집니다.

    */

}
