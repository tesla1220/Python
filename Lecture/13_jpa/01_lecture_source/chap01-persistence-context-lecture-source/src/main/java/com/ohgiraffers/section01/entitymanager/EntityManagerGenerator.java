package com.ohgiraffers.section01.entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EntityManagerGenerator {


//    public EntityManagerGenerator(){}

    public static EntityManager getInstance() {

        // 먼저, EntityManagerFactoryGenerator.getInstance()를 호출하여 EntityManagerFactory 객체를 가져옵니다.
        EntityManagerFactory factory = EntityManagerFactoryGenerator.getInstance();

        // 그런 다음, factory.createEntityManager()를 호출하여 새로운 EntityManager 객체를 생성하고 이를 반환합니다.
        return factory.createEntityManager();

    }
}
/*  ⭐️⭐️ 인스턴스 멤버와 클래스 멤버
        인스턴스 멤버(instance member): 클래스 내에서 static 키워드 없이 선언된 필드나 메서드는 인스턴스 멤버입니다.
        이는 객체(인스턴스)가 생성될 때마다 각각의 객체에 속하는 멤버입니다. 인스턴스 멤버는 해당 클래스의 객체를 생성한 후에만 접근할 수 있습니다.

        클래스 멤버(class member): static 키워드가 사용된 필드나 메서드는 클래스 멤버입니다.
        클래스 멤버는 클래스가 로드될 때 한 번 초기화되며, 모든 객체들이 공유합니다. 클래스 멤버는 클래스 이름을 통해 직접 접근할 수 있습니다.*/
