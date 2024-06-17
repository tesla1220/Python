package com.ohgiraffers.restapi.section01.response;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
@RestController
/* Controller + ResponseBody */
@RequestMapping("/response")
public class ResponseTestController {

    /* 문자열 응답 test */
    @GetMapping("/hello")
    public String helloworld() {

        System.out.println("hello world");

        return "hello world!!!";
    }

    /* 기본 자료형 test */
    @GetMapping("/random")
    public int getRandomNumber() {

        return (int)(Math.random() * 10) + 1;
    }

    /* Object 타입 응답 */
    @GetMapping("/message")
    public Message getMessage() {

        return new Message(200, "정상 응답 완료!!!");
    }

    /* List 타입 응답 */
    @GetMapping("/list")
    public List<String> getList() {

        return List.of(new String[] {"햄버거", "피자", "닭가슴살"});
    }

    @GetMapping("/map")
    public Map<Integer, String> getMap() {

        Map<Integer, String> messageMap = new HashMap<>();
        messageMap.put(200, "정상 응답 완료!!");
        messageMap.put(404, "페이지 찾을 수 없음...");
        messageMap.put(500, "서버 내부 오류 -> 개발자의 잘못");

        return messageMap;
    }
    /* 🟠 Map과 같은 제네릭 타입을 사용할 때 반드시 Integer와 같은 참조 타입을 사용해야하는 이유
            Java의 제네릭 타입 매개변수는 참조 타입만 지원합니다.
            int는 원시 타입이므로 제네릭 타입 매개변수로 사용할 수 없습니다.
            오토박싱과 언박싱을 통해 원시 타입과 참조 타입 간의 자동 변환이 이루어집니다.
            Map<Integer, String>과 같은 형태로 제네릭 타입을 사용할 때 타입 안정성이 보장됩니다.
  */

    /* image response
    *   produces 설정을 해주지 않으면 이미지가 텍스트 형태로 전송된다.
    *   produces 는 response header 의 content-type 에 대한 설정이다.
    * */
    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage() throws IOException {

        return getClass().getResourceAsStream("/images/restapi.png").readAllBytes();

    }

    /* ResponseEntity 를 이용한 응답 */
    @GetMapping("/entity")
    public ResponseEntity<Message> getEntity() {

        /* public ResponseEntity<Message> getEntity()
        이 메서드는 ResponseEntity 라는 것을 반환합니다.
        ResponseEntity  => HTTP 응답(웹 서버가 클라이언트에게 보내는 응답)
        <Message> => 응답이 Message 타입의 데이터를 포함하고 있음 */


        return ResponseEntity.ok(new Message(200, "정상응답 맞니?"));

        /* ResponseEntity.ok(...)
            HTTP 상태 코드 200(OK)를 의미합니다. 이는 요청이 성공적으로 처리되었음을 나타냅니다.

        new Message(200, "정상 응답 여부 확인")
            Message라는 객체를 생성합니다. 여기서는 상태 코드 200과 메시지 "정상 응답 여부 확인"을 포함합니다. */
    }




}
