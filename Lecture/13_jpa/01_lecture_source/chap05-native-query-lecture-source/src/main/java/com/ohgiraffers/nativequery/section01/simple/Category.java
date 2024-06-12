package com.ohgiraffers.nativequery.section01.simple;

import jakarta.persistence.*;



@Entity(name = "section01Category")
@Table(name = "tbl_category")
@SqlResultSetMappings(value = {
        /* 1. @Column 으로 매핑 설정이 되어있는 경우 (자동) */
        @SqlResultSetMapping(
                name = "categoryAutoMapping",
                entities = {@EntityResult(entityClass = Category.class)},
                columns = {@ColumnResult(name = "menu_count")}      // "menu_count" 가 존재하지 않기 때문에 가상의 컬럼을 만들어줌
        ),
        /* 2. 매핑 설정을 수동으로 하는 경우 (Entity 내 @Column 애노테이션 필드를 아래와 같이 기술) */
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
