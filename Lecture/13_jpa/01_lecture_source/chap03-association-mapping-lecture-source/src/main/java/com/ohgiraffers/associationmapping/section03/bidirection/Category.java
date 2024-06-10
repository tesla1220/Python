package com.ohgiraffers.associationmapping.section03.bidirection;

import jakarta.persistence.*;

import java.util.List;

// @ToString(exclude = "menuList") ➡️
// 양방향 연관관게가 설정돼있는 엔티티를 toString 메소드에서 참조할 때, 순환 참조가 발생하여 스택 오버 플로우가 발생할 수 있다.
@Entity(name = "bidirection_category")
@Table(name = "tbl_category")
public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;


    /* 필기 : mappedBy
        연관관계의 주인(Owner)
            : 객체의 참조는 둘인데 외래키는 하나인 상황을 해결하기 위해, 두 객체의 연관관계 중 하나를 정해서 테이블의 외래키를 관리
            : 속성은 연관관계의 주인이 아닌 쪽(외래키가 없는 곳)에 사용되며, 주인 엔티티에 연관된 필드를 가리킨다.
            - 본래 연관관계 주인은 테이블에서 외래키가 있는 곳으로 설정해야 하나 반대로 설정하는 것도 가능. but, 성능상 문제로 권장하지 않음
    *   */

    @OneToMany(mappedBy = "category")       // "category" 는 Menu 엔티티 클래스에 @JoinColum 이 있는 category. "주인에 있는 category에 매핑할거야"
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

    public List<Menu> getMenuList() {
        return menuList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
//                ", menuList=" + menuList +
                '}';
    }
    /* Menu 와 Category 서로 참조를 하다가 toString 메소드가 계속 실행되고 있는 상황. 이 때문에 stack overflow 됨. toString 주석 처리 후 오류 해결됨 */

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}