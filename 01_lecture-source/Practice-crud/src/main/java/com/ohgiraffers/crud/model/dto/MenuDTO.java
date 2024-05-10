package com.ohgiraffers.crud.model.dto;

public class MenuDTO {

    private int code;
    private int categoryCode;
    private String name;
    private int price;

    public MenuDTO() {
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "code=" + code +
                ", categoryCode=" + categoryCode +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
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

    public MenuDTO(int code, int categoryCode, String name, int price) {
        this.code = code;
        this.categoryCode = categoryCode;
        this.name = name;
        this.price = price;
    }
}
