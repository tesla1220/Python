package com.ohgiraffers.mapping.section01.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {         // 데이터베이스와 상호작용하는 메서드를 포함하는 리포지토리 클래스를 정의

    /* @PersistenceContext: 이 어노테이션은 EntityManager 를 주입하는 데 사용
    EntityManager 는 JPA 에서 엔티티의 생명 주기를 관리하고 데이터베이스와의 작업을 수행 */
    @PersistenceContext
    private EntityManager entityManager;        //엔티티 매니저를 주입받아 데이터베이스 작업을 수행

    // Member 엔티티를 데이터베이스에 저장하는 메소드
    public void save(Member member) {

        // 주어진 member 객체를 데이터베이스에 영속화(persist)합니다. 이는 새 엔티티를 데이터베이스에 삽입하는 역할을 합니다.
        entityManager.persist(member);

    }

    public String findNameById(String memberId) {

        String jpql = "SELECT m.memberName FROM entityMember m WHERE m.memberId = '" + memberId + "'";

        /* FROM entityMember m:
        entityMember 엔티티를 참조하는 별칭 m을 정의합니다. 이제 쿼리에서 entityMember 대신 m을 사용할 수 있습니다.*/

        return entityManager.createQuery(jpql, String.class).getSingleResult();

        /*  entityManager.createQuery(jpql, String.class): JPQL 쿼리를 실행하기 위한 쿼리 객체를 생성
            getSingleResult(): 쿼리를 실행하고 단일 결과를 반환합니다. 여기서는 회원의 이름을 반환합니다.  */

    }
}
/*  리포지토리는 엔터티에 대한 CRUD(생성, 읽기, 업데이트, 삭제) 작업을 수행하기 위한 메서드를 제공하는 인터페이스 또는 클래스입니다.
Spring Data JPA에서는 리포지토리를 사용하여 데이터 액세스를 추상화하고 단순화합니다.

인터페이스: 일반적으로 JpaRepository, CrudRepository 또는 PagingAndSortingRepository 와 같은 Spring Data 인터페이스를 확장합니다.

메서드: 일반적인 작업(예: 저장, findById, 삭제)에 대한 기본 제공 메서드를 제공하고 사용자 지정 쿼리 메서드로 확장할 수 있습니다.

주석: 일반적으로 @Repository 로 주석을 달아 Spring 관리 컴포넌트임을 나타내지만 Spring Data 저장소 인터페이스를 확장하는 경우 선택 사항인 경우가 많습니다.


Entity 와 Repository 의 주요 차이점 목적:
엔터티: 데이터 모델과 데이터베이스 구조에 대한 매핑을 나타냅니다.
저장소: 데이터 액세스 메커니즘을 제공하고 엔터티에 대한 데이터베이스 상호 작용을 추상화합니다.

용법:
엔터티: 데이터베이스 테이블의 스키마를 정의하고 데이터를 보유하는 데 사용됩니다.
저장소: 저장, 찾기, 업데이트, 삭제 및 사용자 정의 쿼리와 같은 데이터베이스 작업을 수행하는 데 사용됩니다.

Layer:
엔터티: 도메인 모델 계층의 일부입니다.
저장소: 데이터 액세스 계층의 일부입니다.

상호 작용
일반적으로 저장소는 필요한 데이터베이스 작업을 수행하기 위해 하나 이상의 엔터티에서 작동합니다.
예를 들어 MemberRepository는 CRUD 작업을 수행하기 위해 Member 엔터티를 처리합니다.
 */

/*
Service 와 Repository 의 주요 차이점

목적:
서비스: 비즈니스 논리를 포함하고 다양한 구성 요소 간의 작업을 조정합니다.
리포지토리: 데이터베이스와 직접 상호 작용하여 CRUD 작업을 수행합니다.


Layer:
서비스: 비즈니스 로직 레이어.
저장소: 데이터 액세스 계층.


Responsibility:
서비스: 비즈니스 규칙을 적용하고, 트랜잭션을 처리하며, 여러 저장소 또는 서비스와 상호 작용합니다.
저장소: 데이터 액세스 작업 및 데이터베이스 쿼리를 수행합니다. */