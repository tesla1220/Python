package com.ohgiraffers.springdatajpa.menu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_menu")
public class Menu {

    @Id
    @Column(name = "menu_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "orderableStatus")
    private String orderableStatus;




    /* 엔티티 클래스 내부에서 Builder 패턴 구현하기 */
    public Menu menuCode(int var){
        this.menuCode = var;
        return this;
    }

    public Menu menuName(String var){
        this.menuName = var;
        return this;
    }

    public Menu menuPrice(int var){
        this.menuPrice = var;
        return this;
    }

    public Menu categoryCode(int var){
        this.categoryCode = var;
        return this;
    }

    public Menu orderableStatus(String var){
        this.orderableStatus = var;
        return this;
    }

    public Menu builder(){
        return new Menu(menuCode, menuName, menuPrice, categoryCode, orderableStatus);
    }




    protected Menu() {
    }

    public Menu(int menuCode, String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
