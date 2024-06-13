package com.ohgiraffers.nativequery.sectio01.simple;

import com.ohgiraffers.nativequery.section01.simple.Menu;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NativeQueryTests {


    @PersistenceContext
    private EntityManager manager;


    @DisplayName("결과 타입을 정의한 Native query")
    @Test
    @Transactional
    void testNativeQueryByResultType() {

        // given
        int menuCode = 15;

        // when
        String query = "SELECT menu_code, menu_name, menu_price, category_code, orderable_status" + " FROM tbl_menu" + " WHERE menu_code=?";

        Query nativeQuery = manager.createNativeQuery(query, Menu.class).setParameter(1, 15);

        Menu resultMenu = (Menu) nativeQuery.getSingleResult();

        Assertions.assertNotNull(resultMenu);
        Assertions.assertTrue(manager.contains(resultMenu));

        System.out.println("resultMenu : " + resultMenu);
        System.out.println("manager.contains(resultMenu) : " + manager.contains(resultMenu));

    }

    @DisplayName("결과타입 지정할 수 없는 Native Query")
    @Test
    void testNativeQuery() {

        String query = "SELECT menu_name, menu_price FROM tbl_menu";

        List<Object[]> menuList = manager.createQuery(query).getResultList();

        Assertions.assertNotNull(menuList);
        menuList.forEach(
                row -> {
                    for (Object col : row ){
                        System.out.print(col + " ");
                    }
                    System.out.println();
                }
        );
    }
}
