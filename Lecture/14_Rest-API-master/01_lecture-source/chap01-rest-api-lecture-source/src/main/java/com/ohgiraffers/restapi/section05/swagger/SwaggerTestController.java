package com.ohgiraffers.restapi.section05.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

/* @Tag : API 들의 그룹을 만들기 위한 어노테이션(그룹핑 목적) */
@Tag(name = "Spring Boot Swagger 연동 (USER 기능)")
@RestController
@RequestMapping("/swagger")
public class SwaggerTestController {


    private List<UserDTO> users;

    private SwaggerTestController() {
        users = new ArrayList<>();

        // 더미 데이터 만들기
        users.add(new UserDTO(1,"user01","pass01","너구리", LocalDate.now()));
        users.add(new UserDTO(2,"user02","pass02","코알라", LocalDate.now()));
        users.add(new UserDTO(3,"user03","pass03","푸바오", LocalDate.now()));
    }


    /* @Operation
    *   해당하는 api 의 정보를 제공하는 어노테이션
    *   속성
    *   summary : 해당 api 의 간단한 요약을 제공한다.
    *   description : 해당 api 의 상세한 설명을 제공한다. */

    @Operation(summary = "전체 회원 조회", description = "우리 사이트의 전체 회원 목록 조회 ")
    @GetMapping("/users")
    public ResponseEntity<com.ohgiraffers.restapi.section05.swagger.ResponseMessage> findAllUsers() {


        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> responseMap = new HashMap<>();


        responseMap.put("users", users);

        com.ohgiraffers.restapi.section05.swagger.ResponseMessage responseMessage = new com.ohgiraffers.restapi.section05.swagger.ResponseMessage(200, "조회 성공", responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);       // HttpStatus.OK = 200 번


    }

    @Operation(summary = "회원 번호로 회원 조회", description = "회원 번호를 통해 해당되는 회원을 조회한다."
               , parameters = { @Parameter(name = "userNo", description = "사용자 화면에서 넘어오는 user의 PK")})
    @GetMapping("/users/{userNo}")
    public ResponseEntity<com.ohgiraffers.restapi.section05.swagger.ResponseMessage> findUserByNo(@PathVariable int userNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).collect(Collectors.toList()).get(0);

        Map<String, Object> responsMap = new HashMap<>();
        responsMap.put("user",foundUser);


        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200,"조회성공", responsMap));

    }

    @Operation(summary = "신규회원 등록")
    @PostMapping("/users")
    public ResponseEntity<?> regist(@RequestBody UserDTO newUser) {
        // ? : wild Card 어떤 타입이 들어올 지 모를 때 와일드 카드로 설정. 그러나 권장되는 방식은 아님.

        System.out.println("newUser 잘 들어왔는 지 확인 " + newUser);

        // List 는 인덱스 체계이기 때문에, 마지막 요소를 반환하기 위해(사이즈 측정) 1을 빼줌
        int lastUserNo = users.get(users.size() - 1).getNo();

        // 새로운 유저의 no는 lastUserNo 에 1을 더해주면 됨
        newUser.setNo(lastUserNo + 1);

        users.add(newUser);


        return ResponseEntity.created(URI.create("/entity/users/" + users.get(users.size() -1).getNo()))
                .build();

    }

    @Operation(summary = "회원정보 수정")
    @PutMapping("/users/{userNo}")
    public ResponseEntity<?> modifyUser(@PathVariable int userNo, @RequestBody com.ohgiraffers.restapi.section02.responseentity.UserDTO modifyInfo){

        // userNo 값으로 한 행을 식별. 식별한 한 행을 foundUser 에 넣어줌
        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).collect(Collectors.toList()).get(0);

        foundUser.setId(modifyInfo.getId());
        foundUser.setPwd(modifyInfo.getPwd());
        foundUser.setName(modifyInfo.getName());

        // 수정이므로 다시 재탄생 => created 사용
        return ResponseEntity.created(URI.create("/entity/users/" + userNo)).build();

    }

    @Operation(summary = "회원정보 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "회원정보 삭제 성공")      // PathVaiable 로 받아오는 값을 토대로 정상적으로 잘 작동했을 때
            ,@ApiResponse(responseCode = "400", description = "잘못 입력된 파라미터")

    })
    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<?> removeUser(@PathVariable int userNo){

        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).collect(Collectors.toList()).get(0);

        users.remove(foundUser);

        return ResponseEntity.noContent().build();

    }




}
