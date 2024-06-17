package com.ohgiraffers.restapi.section03.valid;

// Exception 이 발생했을 때 사용자 화면에 어떤 식으로 정보를 뿌릴 지 설정

public class ErrorResponse {

    private String code;
    private String description;
    private String detail;


    public ErrorResponse() {
    }

    public ErrorResponse(String code, String description, String detail) {
        this.code = code;
        this.description = description;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
