package com.ohgiraffers.springdatajpa.menu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_category")
public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    /* ️⭐⭐⭐⭐  refCategoryCode 필드가 int 타입으로 정의되어 있어서 null 값을 허용하지 않아 계속 오류남.
    이를 해결하려면 refCategoryCode 를 Integer 와 같은 래퍼 클래스 타입으로 변경. 래퍼 클래스는 null 값을 허용하기 때문에 오류 해결됨 ⭐⭐⭐⭐*/


    public Category() {
    }

    public Category(int categoryCode, String categoryName, Integer refCategoryCode) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
    }


    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
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
