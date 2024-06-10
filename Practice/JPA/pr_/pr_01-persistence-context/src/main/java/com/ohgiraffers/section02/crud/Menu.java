package com.ohgiraffers.section02.crud;

import jakarta.persistence.*;

@Entity(name = "section02Menu")
@Table(name = "tbl_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  @GenerateValue : auto Increment  사용하기 위해 사용
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;

    // protected : 같은 패키지 내에 있는 녀석들만 사용 가능. 즉 이 경우 crud 에서만 사용 가능
    protected Menu() {}

    public Menu(String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {

        return this.menuCode;
    }

    // 내가 전달한 menuName 을 이 엔티티 필드에 menuName 이라고 매칭해줌
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return menuName;
    }
}
