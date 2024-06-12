package com.ohgiraffers.jpql.section07.namedquery;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NamedQueryRepository {

    @PersistenceContext
    private EntityManager manager;


    public List<Menu> selectByDynamicQuery(String searchName, int searchCode) {

        /* StringBuilder 사용 이유
        String 클래스는 공간 보장 안함. 문자열 합치기 불가. 문자열 합치기 실행하면 합쳐지는 문자에 대한 인스턴스를 새로 생성함.
        그래서 여기선 StringBuilder 사용 */

        StringBuilder jpql = new StringBuilder("SELECT m FROM section07Menu m ");

        if(searchName != null && !searchName.isEmpty() && searchCode > 0) {
            jpql.append("WHERE ");            // append : 합친다는 뜻
            jpql.append("m.menuName LIKE '%' || :menuName || '%' ");
            jpql.append("AND ");
            jpql.append("m.categoryCode = :categoryCode");
        } else {
            if (searchName != null && !searchName.isEmpty()) {
                jpql.append("WHERE ");
                jpql.append("m.menuCode LIKE '%' || :menuName || '%'");
            } else if(searchCode > 0) {
                jpql.append("WHERE ");
                jpql.append("m.categoryCode = :categoryCode");
            }
        }

        TypedQuery<Menu> query = manager.createQuery(jpql.toString(), Menu.class);
        // 우리가 만든 문자열은 StringBuiler 타입이기 때문에 String type 으로 바꿔주기 위해 toSting 사용

        if(searchName != null && !searchName.isEmpty() && searchCode > 0 ) {
            query.setParameter("menuName", searchName);
            query.setParameter("categoryCode", searchCode);
        } else {
            if(searchName != null && !searchName.isEmpty()) {
                query.setParameter("menuName", searchName);
            } else if(searchCode > 0 ) {
                query.setParameter("categoryCode", searchCode);
            }
        }

        List<Menu> menuList = query.getResultList();

        return menuList;

    }

    public List<Menu> selectByNamedQuery() {

        List<Menu> menuList = manager.createNamedQuery("section07Menu.selectMenuList", Menu.class).getResultList();

        return menuList;

    }
}
