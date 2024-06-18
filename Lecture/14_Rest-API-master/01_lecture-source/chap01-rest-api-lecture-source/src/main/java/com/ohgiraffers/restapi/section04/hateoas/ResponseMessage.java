package com.ohgiraffers.restapi.section04.hateoas;

import java.util.Map;

// ResponseMessage 객체는 상태 코드, 메시지, 데이터를 포함하여 HTTP 응답으로 사용됩니다.
public class ResponseMessage {

    private int httpStatusCode;
    private String message;
    private Map<String, Object> results;
    // <String, Object> 는 다음과 같은 방식으로 표기하기 위함.  e.g) "httpStatusCode" : 200   "message" : 정상 조회

    public ResponseMessage() {}

    public ResponseMessage(int httpStatusCode, String message, Map<String, Object> results) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.results = results;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getResults() {
        return results;
    }

    public void setResults(Map<String, Object> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "httpStatusCode=" + httpStatusCode +
                ", message='" + message + '\'' +
                ", results=" + results +
                '}';
    }
}
