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

        String jpql = "SELECT m FROM section02Menu m WHERE m.menuName = :theMenuName";

        /*  This query selects all Menu objects from the section02Menu entity where the menuName field matches the provided parameter. */

        List<Menu> resultMenuList = manager.createQuery(jpql, Menu.class)
                                            .setParameter("theMenuName", menuName) //"menuName" key . menuName 은 우리가 매개변수로 전달한 값
                                            .getResultList();

        // setParameter("theMenuName", menuName)
        // "theMenuName" -> JPQL 쿼리에서 :theMenuName 명명된 파라미터의 값을 설정
        // menuName (두 번째 인수) -> 쿼리에서 :theMenuName 을 대체할 실제 값

        /* manager.createQuery(jpql, Menu.class)
        : This creates a typed query using the JPQL string and specifies Menu.class as the type of the result.

        .setParameter("theMenuName", menuName)
        : This sets the named parameter :theMenuName in the JPQL query to the value of menuName provided when the method is called.

        .getResultList()
        : This executes the query and returns the result as a list of Menu objects. */

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
