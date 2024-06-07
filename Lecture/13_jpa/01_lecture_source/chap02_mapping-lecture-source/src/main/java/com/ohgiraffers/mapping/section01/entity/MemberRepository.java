package com.ohgiraffers.mapping.section01.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    /* @PersistenceContext : Entity 매니저를 주입받아 영속성 컨텍스트에 우리가 만든 Entity 를 관리할 수 있도록 한다. */
    @PersistenceContext     // 즉, PC 에 전달할 매니저를 만들기 위해 @PersistenceContext 추가
    private EntityManager entityManager;

    public void save(Member member) {

        entityManager.persist(member);

    }

    public String findNameById(String memberId) {

        String jpql = "SELECT m.memberName FROM entityMember m WHERE m.memberId = '" + memberId + "'";

        return entityManager.createQuery(jpql, String.class).getSingleResult();

    }
}
