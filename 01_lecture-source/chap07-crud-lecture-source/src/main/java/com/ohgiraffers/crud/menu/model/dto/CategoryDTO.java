package com.ohgiraffers.crud.menu.model.dto;

public class CategoryDTO {

    private int code;
    private String name;
    private int refCategoryCode;

    public CategoryDTO() {
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
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

    public int getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(int refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    public CategoryDTO(int code, String name, int refCategoryCode) {
        this.code = code;
        this.name = name;
        this.refCategoryCode = refCategoryCode;
    }
}

/*
이런 식의 DTO 클래스는 데이터 전송이나 비즈니스 로직과 뷰 간의 데이터 전달에 사용됩니다.
데이터를 담고 있는 객체를 만들어 뷰에서 이를 표시하거나,
데이터를 검색한 후 그 결과를 서비스나 컨트롤러로 전달할 때 사용됩니다.
 */