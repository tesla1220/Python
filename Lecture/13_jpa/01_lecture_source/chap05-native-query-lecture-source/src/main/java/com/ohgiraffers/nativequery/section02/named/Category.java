package com.ohgiraffers.nativequery.section02.named;

import jakarta.persistence.*;


@Entity(name = "section02Category")
@Table(name = "tbl_category")
@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "categoryCountAutoMapping2",
                entities = {@EntityResult(entityClass = Category.class)},
                columns = {@ColumnResult(name = "menu_count")}
        )
})
@NamedNativeQueries( value = {
        @NamedNativeQuery(
                name = "Category.menuCountOfCategory",
                query = "SELECT a.category_code, a.category_name, a.ref_category_code, COALESCE(v.menu_count, 0) menu_count"
                        + " FROM tbl_category a"
                        + " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code)"
        )
})

/* SELECT a.category_code, a.category_name, a.ref_category_code, COALESCE(v.menu_count, 0) menu_count:

    a.category_code, a.category_name, a.ref_category_code:
        tbl_category 테이블의 컬럼입니다.

    COALESCE(v.menu_count, 0) menu_count:
        v.menu_count 가 NULL 인 경우 0으로 대체합니다. 이렇게 하면 조인된 결과가 없는 경우에도 0을 반환합니다.

    FROM tbl_category a:
        tbl_category 테이블을 a라는 별칭으로 선택합니다.

    LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code FROM tbl_menu b GROUP BY b.category_code) v ON (a.category_code = v.category_code):

        LEFT JOIN:
            LEFT JOIN 은 왼쪽 테이블(tbl_category)의 모든 행을 반환합니다. 오른쪽 테이블(서브쿼리)의 매칭되는 행이 없으면 NULL 을 반환합니다.
            즉, tbl_category 에 있는 모든 카테고리를 포함하며, 해당 카테고리에 메뉴가 없는 경우에도 카테고리 정보를 반환합니다.

        (...) v:
            서브쿼리 결과를 v라는 별칭으로 사용합니다. 이는 마치 서브쿼리가 하나의 테이블처럼 동작합니다.

        ON (a.category_code = v.category_code):
            tbl_category 테이블(a)과 서브쿼리 결과(v)를 category_code 를 기준으로 조인합니다.
            tbl_category 의 category_code 와 서브쿼리 결과의 category_code 가 일치하는 행을 찾습니다.

    ORDER BY 1:
        첫 번째 컬럼(category_code)을 기준으로 결과를 정렬합니다.

결과 매핑:
    resultSetMapping = "categoryCountAutoMapping2":
    쿼리 결과를 특정 엔티티나 DTO 에 매핑하기 위한 매핑 설정입니다. categoryCountAutoMapping2라는 이름의 결과 매핑이 정의되어 있어야 합니다.*/

public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;
    // Integer 에는 null 값 포함 가능. int 에는 null 값 포함 불가. 왜? 기본자료형에는 null 값을 포함할 수 없다.

    protected Category() {
    }

    public Category(int categoryCode, String categoryName, Integer refCategoryCode) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }
}
