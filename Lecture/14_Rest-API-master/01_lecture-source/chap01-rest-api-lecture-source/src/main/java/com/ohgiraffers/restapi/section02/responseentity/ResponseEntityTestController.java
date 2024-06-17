package com.ohgiraffers.restapi.section02.responseentity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entity")
public class ResponseEntityTestController {

    /* 필기.
    *   ResponseEntity 란?
    *   결과 데이터와 HTTP 상태 코드를 직접 제어할 수 있는 클래스이다.
    *   HttpStatus, HttpHeaders, HttpBody 를 포함하고 있다. => 즉 ResponseEntity 가 제어할 수 있음
    *  */

    private List<UserDTO> users;

    /* 테스트 위해 아래처럼 데이터베이스처럼 만들어줌 */
    public ResponseEntityTestController() {
        users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "너구리", LocalDate.now()));
        users.add(new UserDTO(2, "user02", "pass02", "코알라", LocalDate.now()));
        users.add(new UserDTO(3, "user03", "pass03", "푸바오", LocalDate.now()));
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {


        HttpHeaders headers = new HttpHeaders();
        // 새로운 HttpHeaders 객체를 생성합니다. 이 객체는 HTTP 응답의 헤더를 설정하는 데 사용됩니다.

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        //응답 데이터를 저장할 HashMap을 생성합니다. 이 맵은 키-값 쌍으로 데이터를 저장합니다.
        Map<String, Object> responseMap = new HashMap<>();
        // responseMap에 "users"라는 키와 users라는 값을 넣습니다. 값 users는 위의 임시 데이터베이스에서 가져온 사용자 목록
        responseMap.put("users", users);

        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);       // HttpStatus.OK = 200 번

        /*     public ResponseEntity<ResponseMessage> findAllUsers()
                이 메서드는 findAllUsers 라는 이름을 가지며, 반환 타입은 ResponseEntity<ResponseMessage>입니다.
                ResponseEntity
                    전체 HTTP 응답(상태 코드, 헤더, 본문)을 캡슐화합니다.
                ResponseMessage
                    응답 본문으로 사용되는 객체입니다. */

        /*   headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            headers 객체:
                HttpHeaders 클래스의 객체인 headers는 HTTP 응답 헤더를 다루기 위해 생성되었습니다.

            headers.setContentType(...) 메서드:
                headers 객체의 setContentType 메서드는 응답의 콘텐츠 타입을 설정하는 데 사용됩니다.
                콘텐츠 타입(Content-Type)은 클라이언트에게 응답 본문의 데이터 형식이 무엇인지 알려줍니다.

            new MediaType("application", "json", Charset.forName("UTF-8")):
                MediaType 객체를 생성합니다.
                이 객체는 콘텐츠 타입을 나타내며, 세 가지 매개변수를 받습니다:
                    "application": 미디어 타입의 기본 유형입니다. 여기서는 application 유형을 사용합니다.
                    "json": 미디어 타입의 하위 유형입니다. 여기서는 JSON 형식을 나타내기 위해 json을 사용합니다.
                    Charset.forName("UTF-8"): 문자 인코딩을 지정합니다. 여기서는 UTF-8 인코딩을 사용합니다.         */

      /*    ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", responseMap);

            2.1. ResponseMessage responseMessage
            새로운 ResponseMessage 객체를 생성합니다. 변수 이름은 responseMessage입니다.

            2.2. new ResponseMessage(...)

            new 키워드를 사용하여 ResponseMessage 클래스의 새 인스턴스를 생성합니다. 생성자(Constructor)를 호출합니다.

            2.3. 생성자 매개변수 (200, "조회 성공", responseMap)
            생성자는 ResponseMessage 객체를 초기화할 때 사용됩니다. 여기서 세 개의 매개변수를 전달합니다:
                200: HTTP 상태 코드를 나타냅니다. 200은 "성공"을 의미합니다.
                "조회 성공": 응답 메시지를 나타냅니다. 여기서는 "조회 성공"이라는 메시지를 사용합니다.
                responseMap: 응답 데이터를 담고 있는 Map<String, Object> 객체입니다. 이 맵에는 조회된 사용자 목록이 포함되어 있습니다. */

        /*   return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
           * 2.1. new ResponseEntity<>(...)

            ResponseEntity 클래스의 새 인스턴스를 생성합니다.
            제네릭 타입은 명시적으로 작성되지 않았지만, 컴파일러가 컨텍스트를 통해 추론할 수 있습니다.

             2.2. 생성자 매개변수

                ResponseEntity 생성자는 세 개의 매개변수를 받습니다:

                    responseMessage (응답 본문)
                        이 객체는 응답 데이터입니다.
                        ResponseMessage 객체로, 상태 코드, 메시지, 데이터(사용자 목록)를 포함하고 있습니다.

                    headers (응답 헤더)
                        이 객체는 HTTP 응답 헤더를 설정합니다.
                        HttpHeaders 객체로, 콘텐츠 타입을 "application/json; charset=UTF-8"로 설정합니다.

                    HttpStatus.OK (HTTP 상태 코드)
                        이 상수는 HTTP 상태 코드를 나타냅니다.
                        HttpStatus.OK는 200을 의미하며, 이는 요청이 성공적으로 처리되었음을 나타냅니다.

                3. 요약

                이 줄의 코드는 다음과 같은 HTTP 응답을 생성하고 반환합니다:

                    응답 본문: responseMessage 객체로, 조회된 사용자 목록과 상태 메시지("조회 성공")를 포함합니다.
                    응답 헤더: headers 객체로, 콘텐츠 타입을 "application/json; charset=UTF-8"로 설정합니다.
                    HTTP 상태 코드: HttpStatus.OK로, 요청이 성공적으로 처리되었음을 나타냅니다.

      */

    }



    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        // 한 행을 뽑아온 다음에 (NO가 동일하면), 리스트를 만들어주고, index 0 가져옴
        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).collect(Collectors.toList()).get(0);

        Map<String, Object> responsMap = new HashMap<>();
        responsMap.put("user",foundUser);

        /* 메소드 체이닝 */
        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200,"조회성공", responsMap));

    }

    @PostMapping("/users")
    public ResponseEntity<?> regist(@RequestBody UserDTO newUser) {
        // ? : wild Card 어떤 타입이 들어올 지 모를 때 와일드 카드로 설정. 그러나 권장되는 방식은 아님.

        System.out.println("newUser 잘 들어왔는 지 확인 " + newUser);

        // List 는 인덱스 체계이기 때문에, 마지막 요소를 반환하기 위해(사이즈 측정) 1을 빼줌
        int lastUserNo = users.get(users.size() - 1).getNo();

        // 새로운 유저의 no는 lastUserNo 에 1을 더해주면 됨
        newUser.setNo(lastUserNo + 1);

        users.add(newUser);

        // 조회에 성공했다 = 1명의 회원을 만들음 => created 사용
        // created : 상태코드는 201. 즉, 등록 관련(생성)
        // "/entity/users/" + users.get(users.size() -1).getNo()) => list 의 마지막 데이터의 No 꺼내오기
        return ResponseEntity.created(URI.create("/entity/users/" + users.get(users.size() -1).getNo()))
                .build();

    }

    /* 수정 */
    @PutMapping("/users/{userNo}")
    public ResponseEntity<?> modifyUser(@PathVariable int userNo, @RequestBody UserDTO modifyInfo){

        // userNo 값으로 한 행을 식별. 식별한 한 행을 foundUser 에 넣어줌
        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).collect(Collectors.toList()).get(0);

        foundUser.setId(modifyInfo.getId());
        foundUser.setPwd(modifyInfo.getPwd());
        foundUser.setName(modifyInfo.getName());

        // 수정이므로 다시 재탄생 => created 사용
        return ResponseEntity.created(URI.create("/entity/users/" + userNo)).build();

    }

    /* 삭제 */
    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<?> removeUser(@PathVariable int userNo){

        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).collect(Collectors.toList()).get(0);

        users.remove(foundUser);

        return ResponseEntity.noContent().build();

    }

    /* ⭐️ ResponseMessage 클래스

    ResponseMessage 클래스는 HTTP 응답 메시지를 구조화하는 데 사용되는 사용자 정의 클래스입니다.
    이 클래스에는 일반적으로 상태 코드, 메시지, 데이터 등을 포함하는 속성들이 있습니다.
    예를 들어, ResponseMessage 클래스는 다음과 같은 필드를 가질 수 있습니다:
    int statusCode: HTTP 상태 코드를 저장하는 필드.
    String message: 응답 메시지를 저장하는 필드.
    Map<String, Object> data: 응답 데이터를 저장하는 필드.


    */


}
