package com.ohgiraffers.nativequery.section01.simple;

import jakarta.persistence.*;


@Entity(name = "section01Category")
@Table(name = "tbl_category")
@SqlResultSetMappings(value = {@SqlResultSetMapping(
        name = "categoryAutoMapping",
        entities = {@EntityResult(entityClass = Category.class)},
        columns = {@ColumnResult(name = "menu_count")}
),
        @SqlResultSetMapping(
                name = "categoryManualMapping",
                entities = {
                        @EntityResult(entityClass = Category.class,
                                      fields = {@FieldResult(name = "categoryCode", column = "category_code"),
                                                @FieldResult(name = "categoryName", column = "category_name"),
                                                @FieldResult(name = "refCategoryCode", column = "ref_category_code")
                                      }
                        )
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
