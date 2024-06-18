package com.ohgiraffers.restapi.section04.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/hateoas")
public class HateoasTestController {

    /* 필기 : Hateoas
               하나의 API 를 동작시켰을 때, 그 전 상황이나 그 후 상황에 대한 링크를 제공함으로서
               클라이언트의 리소스(자원)에 대한 이해를 돕도록 링크를 제공하는 것
               => RESTFul API 라고 한다.  */


    private List<UserDTO> users;

    private HateoasTestController() {
        users = new ArrayList<>();

        // 더미 데이터 만들기
        users.add(new UserDTO(1,"user01","pass01","너구리", LocalDate.now()));
        users.add(new UserDTO(2,"user02","pass02","코알라", LocalDate.now()));
        users.add(new UserDTO(3,"user03","pass03","푸바오", LocalDate.now()));
    }

    // 유저 번호로 유저 조회
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        UserDTO foundUser = users
                .stream()
                .filter(user -> user.getNo() == userNo)
                .collect(Collectors.toList())
                .get(0);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);


        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200,"조회 성공!!!", responseMap));
    }


    /* Hateoas 적용 전체 조회 */
    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {

        // header 구성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        /* EntityModel : 헤이테오스에서 제공하는 클래스로 rest api 를 만들 때 해당 리소스와 관련된 링크를 포함할 수 있게 함 */
        List<EntityModel<UserDTO>> userWithRel
                = users.stream().map(user -> EntityModel.of(
                        user,                                    // 클래스에 대한 메타 정보 제공하면 해당 클래스 안 메소드 사용 가능
                        linkTo(methodOn(HateoasTestController.class).findUserByNo(user.getNo())).withSelfRel(),
                linkTo(methodOn(HateoasTestController.class).findAllUsers()).withRel("users")
        )).collect(Collectors.toList());

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", userWithRel);

        ResponseMessage responseMessage = new ResponseMessage(200,"조회 성공!!!!!",responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

}
