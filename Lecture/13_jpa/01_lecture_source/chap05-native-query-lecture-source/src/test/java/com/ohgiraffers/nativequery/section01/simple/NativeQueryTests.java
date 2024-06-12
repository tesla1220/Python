package com.ohgiraffers.nativequery.section01.simple;

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

    /* 필기 "Native Query"
     *       : MySQL 에서 작성했던 쿼리문을 그대로 사용
     *       : ORM 의 기능을 이용하면서, SQL 쿼리를 사용할 수 있기 때문에 데이터베이스 접근에 더욱 용이
     *       - Native Query 는 언제 사용할까?
     *           : 복잡한 쿼리 작성할 때
     *           : 특정 데이터베이스에서만 사용 가능한 기능을 사용해야할 때
     * */

    /* 필기
     *   1. 결과 타입 정의 가능할 때
     *   2. 결과 타입 정의 불가할 때
     *   3. 결과 매핑 사용 */

    @PersistenceContext
    private EntityManager manager;

    /* 1. 결과 타입 정의한 경우 */
    /* 필기
        모든 컬럼값을 매핑하는 경우에만 타입을 특정할 수 있다.
        일부 컬럼만 조회를 하고 싶은 경우 -> Object[], 스칼라값을 별도로 담을 클래스를 정의해서 사용해야한다.
     */

    @DisplayName("결과 타입을 정의한 Native query 사용해보기")
    @Test
    @Transactional
    void testNativeQueryByResultType() {

        // given
        int menuCode = 15;

        // when
        String query = "SELECT menu_code, menu_name, menu_price, category_code, orderable_status "
                + " FROM tbl_menu"
                + " WHERE menu_code = ?";       // 위치기반으로 조회할 것이므로 물음표 사용

        Query nativeQuery = manager.createNativeQuery(query, Menu.class).setParameter(1, menuCode);
        // setParameter(1, menuCode) : 1번째 물음표에 메뉴코드를 넣어줌

        Menu foundMenu = (Menu) nativeQuery.getSingleResult();
        // Query 는 Object 타입이므로 형변환 해줌

        Assertions.assertNotNull(foundMenu);    // 결과 담겼는 지 확인
        Assertions.assertTrue(manager.contains(foundMenu));     // Persistence Context 에도 담겨있는 지 확인
        System.out.println("foundMenu : " + foundMenu);


        // @Transactional 했을 때 왜 오류날까? -> Persistence Context 에 담겨있는 지 확인 불가
        // 즉, 매니저가 가지고 있는 지 아닌 지 여부에 따라 매니저가 가지고 있으면 Transactional 붙여야 함 . . .

    }

    @DisplayName("결과 타입을 지정할 수 없는 Native Query 테스트")
    @Test
    void testNativeQueryNonResultType() {

        String query = "SELECT menu_name, menu_price FROM tbl_menu";

        List<Object[]> menuList = manager.createNativeQuery(query).getResultList();

        Assertions.assertNotNull(menuList);
        menuList.forEach(
                row -> {
                    for (Object col : row) {
                        System.out.print(col + " ");
                    }
                    System.out.println();
                }
        );
    }




    /* 자료 넣을 때 자료형 지정해줘야하는데 타입이 Object 일 때 데이터베이스에 저장할 수 없으므로 */

    /* 필기
            "자동 매핑을 사용하는 이유"
                데이터베이스에 값을 넣을 때 엔티티와 데이터 타입을 항상 일치시켜야 한다.
                하지만 반환값이 Object 인 경우, 우리가 원하는 데이터 타입으로 형변환을 해줘야 한다.
                이는 entity 필드와 데이터베이스의 자료형 모두 고려해야하므로 번거롭다.                  */



    /* 3. 결과 매핑을 사용하는 경우 => 자동 vs 수동 */
    @DisplayName("자동 결과 매핑을 사용한 Native Query 조회 테스트")
    @Test
    @Transactional
    void testAutoMapping() {

        /* 상황 : category 를 기준으로 카테고리 코드별 메뉴 개수를 조회할거임 */

        // COALESCE 함수 : 여러 개의 인수(컬럼)를 전달할 수 있으며, Left Join 으로 인해 null 이 발생할 수 있기 때문에 null 이 발생할 경우 0을 넣어주겠다
        String query = "SELECT a.category_code, a.category_name, a.ref_category_code, COALESCE(v.menu_count, 0) menu_count" +
                " FROM tbl_category a" +
                " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code" +
                " FROM tbl_menu b " +
                " GROUP BY b.category_code) v ON (a.category_code = v.category_code)" +
                " ORDER BY 1";



        Query nativeQuery = manager.createNativeQuery(query, "categoryAutoMapping");

        List<Object[]> categoryList = nativeQuery.getResultList();

        Assertions.assertNotNull(categoryList);
        Assertions.assertTrue(manager.contains(categoryList.get(0)[0]));

        categoryList.forEach(
                row -> {
                    for (Object col : row) {
                        System.out.print(col + " : ");
                    }
                    System.out.println();
                }
        );

        // (v.menu_count, 0) ->  null 이 발생할 경우 0으로 대체해서 넣어주겠다

    }


    @DisplayName("수동 결과 매핑을 사용한 Native Query 조회 테스트")
    @Test
    @Transactional
    void testManualMapping() {


        String query = "SELECT a.category_code, a.category_name, a.ref_category_code, COALESCE(v.menu_count, 0) menu_count" +
                " FROM tbl_category a" +
                " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code" +
                " FROM tbl_menu b " +
                " GROUP BY b.category_code) v ON (a.category_code = v.category_code)" +
                " ORDER BY 1";


        Query nativeQuery = manager.createNativeQuery(query, "categoryManualMapping");

        List<Object[]> categoryList = nativeQuery.getResultList();

        Assertions.assertNotNull(categoryList);
        Assertions.assertTrue(manager.contains(categoryList.get(0)[0]));

        categoryList.forEach(
                row -> {
                    for (Object col : row) {
                        System.out.print(col + " : ");
                    }
                    System.out.println();
                }
        );


    }

}

/* SQL 해석
*         String query = "SELECT a.category_code, a.category_name, a.ref_category_code, COALESCE(v.menu_count, 0) menu_count" +
                " FROM tbl_category a" +
                " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code" +
                " FROM tbl_menu b " +
                " GROUP BY b.category_code) v ON (a.category_code = v.category_code)" +
                " ORDER BY 1";


         COALESCE(v.menu_count, 0) menu_count
            ▶️️ v라는 임시 테이블의 menu_count 컬럼을 선택
            COALESCE 함수는 menu_count 가 NULL 이면 0을 반환
            이를 menu_count 라는 이름으로 선택합니다. 이 부분은 각 카테고리에 속한 메뉴의 개수를 나타냅니다.

           LEFT JOIN
            ▶️ 두 테이블을 결합하지만, 왼쪽 테이블(tbl_category)의 모든 행을 포함시킵니다. 오른쪽 테이블(tbl_menu)에 일치하는 행이 없으면 NULL 을 반환합니다.

           (SELECT COUNT(*) AS menu_count, b.category_code FROM tbl_menu b GROUP BY b.category_code) v
             ▶️ 하위 쿼리입니다.


            SELECT COUNT(*) AS menu_count, b.category_code:

            COUNT(*) AS menu_count
             ▶️ tbl_menu 테이블에서 각 카테고리(b.category_code)에 속한 메뉴의 개수를 세어 menu_count 라는 이름으로 지정합니다.

            b.category_code
             ▶️ tbl_menu 테이블의 category_code 컬럼을 선택합니다. 이 컬럼은 메뉴가 속한 카테고리의 코드를 나타냅니다.


           FROM tbl_menu b
             ▶️tbl_menu 테이블을 b라는 별칭으로 지정합니다.

           GROUP BY b.category_code
             ▶️ category_code 별로 메뉴 개수를 그룹화합니다.

           이 하위 쿼리는 각 category_code 별로 메뉴 개수를 계산하여 v라는 임시 테이블을 만듭니다.

           ON (a.category_code = v.category_code)
              ▶️ 조인의 조건을 지정합니다. tbl_category 테이블의 category_code 와 하위 쿼리의 category_code 가 일치하는 행을 결합합니다.


             ▶️▶️ 요약 : SELECT COUNT(*) AS menu_count, b.category_code 쿼리는 각 카테고리 코드에 해당하는 메뉴의 개수를 계산하여 반환
             tbl_category 테이블과 tbl_menu 테이블을 조인하여 각 카테고리의 코드, 이름, 참조 카테고리 코드, 그리고 각 카테고리에 속한 메뉴의 개수를 가져옵니다.
             LEFT JOIN 을 사용하여 tbl_category 에 포함된 모든 카테고리를 포함시키고, 메뉴가 없는 카테고리의 경우 메뉴 개수를 0으로 설정합니다. 결과는 category_code 순서로 정렬됩니다.

*/
