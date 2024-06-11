package com.ohgiraffers.jpql.section02.parameter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParameterBindingRepository {

    @PersistenceContext
    private EntityManager manager;


    public List<Menu> selectMenuByBindingName(String menuName) {

        String jpql = "SELECT m FROM section02Menu m WHERE m.menuName = :menuName";

        List<Menu> resultMenuList = manager.createQuery(jpql, Menu.class)
                                            .setParameter("menuName", menuName) //"menuName" key . menuName 은 우리가 매개변수로 전달한 값
                                            .getResultList();

        // setParameter("menuName", menuName)
        // "menuName" -> JPQL 쿼리에서 :menuName 명명된 파라미터의 값을 설정
        // menuName (두 번째 인수) -> 쿼리에서 :menuName 을 대체할 실제 값

        return resultMenuList;


    }

    public List<Menu> selectMenuByBindingPosition(String menuName) {

        String jpql = "SELECT m FROM section02Menu m WHERE m.menuName = ?1";

        List<Menu> resultMenuList = manager.createQuery(jpql, Menu.class)
                .setParameter(1, menuName)
                .getResultList();

        return resultMenuList;
    }
}
