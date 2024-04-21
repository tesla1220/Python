package com.ohgiraffers.crud.menu.model.dto;

public class MenuDTO {
    private int code;
    private String name;
    private int price;
    private int categoryCode;
    private String ordatebleStatus;


    public MenuDTO() {
    }

    public MenuDTO(int code, String name, int price, int categoryCode, String ordatebleStatus) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.categoryCode = categoryCode;
        this.ordatebleStatus = ordatebleStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrdatebleStatus() {
        return ordatebleStatus;
    }

    public void setOrdatebleStatus(String ordatebleStatus) {
        this.ordatebleStatus = ordatebleStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categoryCode=" + categoryCode +
                ", ordatebleStatus='" + ordatebleStatus + '\'' +
                '}';
    }
}
