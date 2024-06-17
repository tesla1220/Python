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
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", users);        // 위에서 만든 임시 DB 를 key 와 value 로 넣어줌

        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);       // HttpStatus.OK = 200 번
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




}
