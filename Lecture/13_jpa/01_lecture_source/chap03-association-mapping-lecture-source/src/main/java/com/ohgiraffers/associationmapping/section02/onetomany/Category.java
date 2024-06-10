package com.ohgiraffers.associationmapping.section02.onetomany;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "category_and_menu")
@Table(name = "tbl_category")
public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;


    // category 는 많은 메뉴들을 갖고 있기 때문에 List 타입으로 생성
    @OneToMany(cascade = CascadeType.PERSIST)    // category 는 하나(one)고 Menu 는 여러개(many)
    @JoinColumn(name = "category_code")          // category_code 로 menuList 와 join
    private List<Menu> menuList;


    // Entity 요건 추가 ⬇️⬇️⬇️

    public Category() {
    }

    public Category(int categoryCode, String categoryName, Integer refCategoryCode, List<Menu> menuList) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
        this.menuList = menuList;
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

    public List<Menu> getMenuList() {
        return menuList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                ", menuList=" + menuList +
                '}';
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}