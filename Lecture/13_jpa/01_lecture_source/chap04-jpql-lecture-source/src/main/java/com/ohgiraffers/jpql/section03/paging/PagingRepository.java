package com.ohgiraffers.jpql.section03.paging;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PagingRepository {

    @PersistenceContext
    private EntityManager manager;


    public List<Menu> usingPagingAPI(int offset, int limit) {

        String jpql = "SELECT m FROM section03Menu m ORDER BY m.menuCode DESC";
        // 페이징처리용 sql과 문법이 다르기 때문에 sql 문에 offset limit 사용하지 않는다

        List<Menu> pagingMenuList = manager.createQuery(jpql, Menu.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

        return pagingMenuList;

        /*  manager.createQuery(jpql, Menu.class) : JPQL 쿼리 문자열을 사용하여 TypedQuery<Menu> 객체를 생성합니다.
            setFirstResult(offset) : 쿼리 결과의 첫 번째 결과 위치를 설정합니다.
            setMaxResults(limit) : 쿼리가 반환할 최대 결과 수를 설정합니다.
            getResultList() : 쿼리를 실행하고 결과를 Menu 엔티티의 목록으로 반환합니다.    */
    }
}
