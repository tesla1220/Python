package com.ohgiraffers.associationmapping.section01.manytoone;

import jakarta.persistence.*;

@Entity(name = "menu_and_category")
@Table(name = "tbl_menu")
public class Menu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;


    /* 카테고리 코드에 123을 임의로 만들음.
    그러나 카테고리에는 123 카테고리번호가 존재하지 않기 때문에 우리가 해당 내용 담아 작성한 Insert 테스트가 실행되지 않는 게 맞다. */

    /* 필기
    *   영속성 전이
    *       : 특정 엔티티를 영속화(등록)할 때, 연관된 엔티티도 함꼐 영속화한다는 의미다.
    *       : 즉, Menu 엔티티를 영속화할 때, 이와 연관된 Category 도 같이 영속화 시킨다. */
    @ManyToOne(cascade = CascadeType.PERSIST)      // 하나의 카테고리 코드는 많은 메뉴코드를 갖고 카테고리가 다. 메뉴가 1.
    @JoinColumn(name = "category_code")
    private Category category;

    @Column(name = "orderable_status")
    private String orderableStatus;

    protected Menu() {}

    public Menu(int menuCode, String menuName, int menuPrice, Category category, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }

    // 엔티티는 getter 만 추가함!

    public int getMenuCode() {
        return menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public Category getCategory() {
        return category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName=" + menuName +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
