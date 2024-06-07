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
