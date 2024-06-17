package com.ohgiraffers.restapi.section01.response;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/response")
public class ResponseTestController {


    @GetMapping("/hello")
    public String helloworld(){

        System.out.println("Wasssssssup");

        return "Wasssssssup";
    }

    @GetMapping("/random")
    public int getRandomNumber() {

        return (int)(Math.random() * 10 ) + 1;
    }

    @GetMapping("/message")
    public Message getMessage() {

        return new Message(200, "정상 응답 완료!!");
    }

    @GetMapping("/list")
    public List<String> getList(){

        return List.of(new String[] {"burger, pizza, salad"});
    }

    @GetMapping("/map")
    public Map<Integer, String> getMap() {

        Map<Integer, String> messageMap = new HashMap<>();
        messageMap.put(200, "정상 응답 완료");
        messageMap.put(404, "페이지 못찾음");
        messageMap.put(500, "서버 내부 오류 -> 개발자 잘못");

        return messageMap;

    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage() throws IOException {

        return getClass().getResourceAsStream("/images/restapi.png").readAllBytes();
    }

    @GetMapping("/entity")
    public ResponseEntity<Message> getEntity() {

        return ResponseEntity.ok(new Message(200, "정상 응답 여부 확인"));
    }

}
