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
    private EntityManager entityManager;

    @DisplayName("결과 타입 정의한 Native Query 사용해보기")
    @Test
    @Transactional
    void testNativeQueryByResultType() {

        // given
        int menuCode = 15;

        // when
        String query = "SELECT menu_code, menu_name, menu_price, category_code, orderable_status"
                + " FROM tbl_menu"
                + " WHERE menu_code = ? ";

        Query nativeQuery = entityManager.createNativeQuery(query, Menu.class).setParameter(1, menuCode);

        Menu menuEntity = (Menu) nativeQuery.getSingleResult();

        Assertions.assertNotNull(menuEntity);

        Assertions.assertTrue(entityManager.contains(menuEntity));

        System.out.println("menuEntity : " + menuEntity);

    }

    @DisplayName("결과 타입 지정할 수 없는 Native Query 사용해보기")
    @Test
    void testNativeQueryNonResultType() {

        String theNativeQuery = "SELECT menu_name, menu_price FROM tbl_menu";

        List<Object[]> reesultMenuList = entityManager.createNativeQuery(theNativeQuery).getResultList();

        Assertions.assertNotNull(reesultMenuList);

        reesultMenuList.forEach(
                row -> {
                    for ( Object col : row ) {
                        System.out.println( col + " ");
                    }
                    System.out.println();
                }
        );

    }

    /* 코드 연습 */
    @DisplayName("Native query 코드 연습: 결과 타입(Menu Entity) 정의 ")
    @Test
    @Transactional
    void testChatGPT() {

        int menuCode = 123;

        String thisIsQuery = "SELECT menu_code, menu_name, menu_price, category_code, orderable_status "
                + "FROM tbl_menu"
                + " WHERE menu_code = ? ";

        Query nativeQuery = entityManager.createNativeQuery(thisIsQuery, Menu.class).setParameter(1, menuCode);

        Menu resultMenuEntity = (Menu) nativeQuery.getSingleResult();

        Assertions.assertNotNull(resultMenuEntity);
        Assertions.assertTrue(entityManager.contains(resultMenuEntity));

        System.out.println("resultMenuEntity : " + resultMenuEntity);
    }

    @DisplayName("Native Query 테스트: 결과 타입 지정X ")
    @Test
    void textNonResultTypeNativeQuery() {

        String thisIsQuery = "SELECT category_name, ref_category_code FROM tbl_category";

        List<Object[]> theCategoryList = entityManager.createNativeQuery(thisIsQuery).getResultList();

        Assertions.assertNotNull(theCategoryList);
        theCategoryList.forEach(
                row -> {
                    for (Object col : row) {
                        System.out.println(col + " ");
                    }
                    System.out.println();
                }
        );


        }


/* 3. 결과 매핑 사용하는 경우 => 자동 vs 수동 */

    @DisplayName("Native Query Test: 자동 결과 매핑을 사용한 조회 테스트 ")
    @Test
    void testAutoMapping() {
        // 카테 고리 기준으로 카테고리 코드별 메뉴 개수 조회
    }

}
