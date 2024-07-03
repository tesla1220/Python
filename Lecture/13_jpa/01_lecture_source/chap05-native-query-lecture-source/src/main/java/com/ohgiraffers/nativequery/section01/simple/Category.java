package com.ohgiraffers.nativequery.section01.simple;

import jakarta.persistence.*;

/*
SQL 결과 매핑:

    @SqlResultSetMappings 어노테이션은 네이티브 SQL 쿼리의 결과를 이 엔티티나 컬럼에 매핑하는 방법을 정의합니다.
    categoryAutoMapping 이라는 이름의 매핑이 정의되어 있습니다. 이 매핑은 두 부분으로 구성됩니다:
    entities: 네이티브 SQL 쿼리의 결과를 Category 엔티티로 매핑합니다.
    columns: 네이티브 SQL 쿼리의 menu_count 컬럼을 특정 속성으로 매핑합니다.

* @SqlResultSetMappings:

    이 어노테이션은 네이티브 SQL 쿼리의 결과를 엔티티 또는 특정 컬럼에 매핑하는 방법을 정의합니다.
    value 속성은 하나 이상의 @SqlResultSetMapping 을 포함하는 배열입니다.

* @SqlResultSetMapping:

    네이티브 SQL 쿼리의 결과를 매핑하는 하나의 정의를 나타냅니다.
    name 속성은 매핑의 이름을 지정합니다. 여기서는 categoryAutoMapping 입니다.
    entities 속성은 @EntityResult 어노테이션을 포함하는 배열입니다. 이는 쿼리 결과를 엔티티로 매핑하는 방법을 정의합니다.
    columns 속성은 @ColumnResult 어노테이션을 포함하는 배열입니다. 이는 쿼리 결과의 특정 컬럼을 매핑하는 방법을 정의합니다.

* @EntityResult:

    네이티브 SQL 쿼리의 결과를 엔티티에 매핑합니다.
    entityClass 속성은 매핑될 엔티티 클래스를 지정합니다. 여기서는 Category.class 입니다.

* @ColumnResult:

    네이티브 SQL 쿼리의 특정 컬럼을 매핑합니다. name 속성은 매핑될 컬럼의 이름을 지정합니다. 여기서는 menu_count 입니다. */


@Entity(name = "section01Category")
@Table(name = "tbl_category")
@SqlResultSetMappings(value = {
        /* 3. 결과 매핑 (자동) : @Column 으로 매핑 설정이 되어있는 경우 (자동) */
        @SqlResultSetMapping(
                name = "categoryAutoMapping",
                entities = {@EntityResult(entityClass = Category.class)},
                columns = {@ColumnResult(name = "menu_count")}      // "menu_count" 가 존재하지 않기 때문에 가상의 컬럼을 만들어줌
        ),
        /* 4. 결과 매핑 (수동) : 매핑 설정을 수동으로 하는 경우 (Entity 내 @Column 애노테이션 필드를 아래와 같이 기술) */
        @SqlResultSetMapping(
                name = "categoryManualMapping",
                entities = {
                        @EntityResult(entityClass = Category.class , fields = {
                                @FieldResult(name = "categoryCode", column = "category_code"),
                                @FieldResult(name = "categoryName", column = "category_name"),
                                @FieldResult(name = "refCategoryCode", column = "ref_category_code")
                        })
                },
                columns = {@ColumnResult(name = "menu_count")}
        )
})

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
