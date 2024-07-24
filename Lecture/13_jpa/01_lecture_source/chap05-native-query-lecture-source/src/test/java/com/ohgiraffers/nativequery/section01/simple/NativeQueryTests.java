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

/*
엔티티 매핑 필요 여부:

1) createNativeQuery(query, Menu.class)는 엔티티 클래스(Menu)로 결과를 매핑할 때 사용됩니다.
즉, 결과가 엔티티 객체로 매핑될 수 있는 구조를 가져야 합니다. 이 방식은 객체 지향적으로 데이터를 다루고자 할 때 유용합니다.

2) createNativeQuery(query)는 엔티티 매핑 없이 결과를 원시 데이터 형태로 처리하고자 할 때 사용됩니다.
이 방식은 엔티티 객체로 매핑하기 힘든 복잡한 쿼리나, 단순히 일부 열만 필요한 경우에 유용합니다.

결과 처리 방식:

엔티티 매핑을 사용하면, 결과를 쉽게 엔티티 객체로 다룰 수 있고, JPA 의 다양한 기능(예: 캐싱, 변경 감지 등)을 사용할 수 있습니다.
원시 데이터 형태로 결과를 받으면, 각 결과를 수동으로 처리해야 하지만 더 유연한 쿼리 작성과 빠른 성능을 기대할 수 있습니다.
*/

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



    /* 필기 1. Native Query 결과 타입 정의한 경우
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
                + " WHERE menu_code = ?";       // ? : Parameter 를 binding 할 수 있는 자리 표시자

        Query nativeQuery = manager.createNativeQuery(query, Menu.class).setParameter(1, menuCode);
        // query 문자열을 네이티브 쿼리로 실행합니다. Menu.class 는 쿼리 결과를 매핑할 엔티티 클래스를 지정합니다.
        // .setParameter(1, menuCode): 첫 번째 매개변수(?)에 menuCode 값을 설정합니다. 여기서는  1 번째 매개변수로 15를 설정합니다.

        Menu foundMenu = (Menu) nativeQuery.getSingleResult();
        // Query 는 Object 타입이므로 형변환 해줌

        Assertions.assertNotNull(foundMenu);
        //  쿼리가 유효한 결과를 반환했는지 확인합니다.
        Assertions.assertTrue(manager.contains(foundMenu));
        // manager가 foundMenu 객체를 관리하고 있는지 확인 = Persistence Context 에도 foundMenu 가 담겨있는 지 확인
        System.out.println("foundMenu : " + foundMenu);


        // @Transactional 안했을 때 왜 오류날까? -> Persistence Context 에 담겨있는 지 확인 불가
        // 즉, 매니저가 가지고 있는 지 아닌 지 여부에 따라 매니저가 가지고 있으면 Transactional 붙여야 함 . . .

        /*  setParameter
        JPQL이나 Native Query를 작성할 때, 쿼리 내에 파라미터를 바인딩할 수 있는 자리 표시자를 사용할 수 있습니다.
        위의 경우 ?는 자리 표시자로 사용됩니다. 이 자리 표시자는 실제 쿼리가 실행될 때 해당 위치에 값을 바인딩해야 합니다.
        setParameter 메서드를 사용하여 이 값을 지정합니다.

         setParameter(1, menuCode);는 첫 번째 자리 표시자(?)에 menuCode 값을 바인딩하는 역할을 합니다.
         여기서 1은 첫 번째 자리 표시자를 의미하며, SQL 쿼리의 파라미터 인덱스를 나타냅니다.

         이러한 방식으로 쿼리를 작성하면 다음과 같은 이점이 있습니다:
            1) SQL 인젝션 방지: 쿼리 파라미터를 직접 문자열로 결합하지 않기 때문에 SQL 인젝션 공격을 예방할 수 있습니다.
            2) 가독성 향상: 쿼리와 파라미터를 분리하여 가독성을 높일 수 있습니다.
            3) 재사용성 증가: 동일한 쿼리를 다양한 파라미터와 함께 사용할 수 있습니다.
 */

        /* 요약
            이 테스트 메서드는 다음과 같은 단계를 수행합니다:

            1. menuCode 를 15로 설정합니다.
            2. menu_code 가 15인 메뉴를 선택하는 네이티브 쿼리를 작성합니다.
            3. 네이티브 쿼리를 실행하고 결과를 Menu 클래스 객체로 매핑합니다.
            4. 쿼리 결과가 null 이 아님을 확인합니다.
            5. 결과 객체가 영속성 컨텍스트에 포함되어 있는지 확인합니다.
            6. 결과 객체를 콘솔에 출력합니다.
*/

    }



    /* 필기 2. Native Query 결과 타입 정하지 않은 경우 */
    @DisplayName("결과 타입을 지정할 수 없는 Native Query 테스트")
    @Test
    void testNativeQueryNonResultType() {

        String query = "SELECT menu_name, menu_price FROM tbl_menu";

        List<Object[]> menuList = manager.createNativeQuery(query).getResultList();
        // 엔티티 매핑이 필요 없음: 결과를 특정 엔티티 클래스에 매핑하지 않고, 일반적인 Object 배열이나 Map 형태로 결과를 받을 수 있습니다.

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

    /* menuList.forEach:
            menuList는 List<Object[]> 타입입니다.
            forEach 메서드는 menuList의 각 요소에 대해 람다식을 실행합니다.

        row -> { ... }:
            row는 menuList의 각 요소로, Object[] 타입입니다.
            람다 표현식으로, menuList의 각 Object[] 요소에 대해 실행할 코드를 정의합니다.

        for (Object col : row):
            row 는 Object[] 배열이므로, for-each 루프를 통해 배열의 각 요소(col)를 순회합니다.
            col 은 Object 타입으로, row 배열의 각 요소를 나타냅니다.*/

    /* 자료 넣을 때 자료형 지정해줘야하는데 타입이 Object 일 때 데이터베이스에 저장할 수 없으므로 */

    /* 필기
            "자동 매핑을 사용하는 이유"
                데이터베이스에 값을 넣을 때 엔티티와 데이터 타입을 항상 일치시켜야 한다.
                하지만 반환값이 Object 인 경우, 우리가 원하는 데이터 타입으로 형변환을 해줘야 한다.
                이는 entity 필드와 데이터베이스의 자료형 모두 고려해야하므로 번거로움 => 자동 매핑 사용                */




    /* 필기 3. 결과 매핑을 사용하는 경우 => 자동 vs 수동 */
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



           ON (a.category_code = v.category_code)
              ▶️ 조인의 조건을 지정합니다. tbl_category 테이블의 category_code 와 하위 쿼리의 category_code 가 일치하는 행을 결합합니다.


             ▶️▶️ 요약 : SELECT COUNT(*) AS menu_count, b.category_code 쿼리는 각 카테고리 코드에 해당하는 메뉴의 개수를 계산하여 반환
             tbl_category 테이블과 tbl_menu 테이블을 조인하여 각 카테고리의 코드, 이름, 참조 카테고리 코드, 그리고 각 카테고리에 속한 메뉴의 개수를 가져옵니다.
             LEFT JOIN 을 사용하여 tbl_category 에 포함된 모든 카테고리를 포함시키고, 메뉴가 없는 카테고리의 경우 메뉴 개수를 0으로 설정합니다. 결과는 category_code 순서로 정렬됩니다.

*/
