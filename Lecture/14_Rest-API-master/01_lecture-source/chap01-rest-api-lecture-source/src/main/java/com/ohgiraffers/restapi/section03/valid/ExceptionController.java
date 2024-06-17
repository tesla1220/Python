package com.ohgiraffers.restapi.section03.valid;


//Exception Handler 클래스

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserNotFoundException.class)      // 'UserNotFoundException' 클래스에 대한 예외 처리를 할 핸들러
    public ResponseEntity<ErrorResponse> handleNotFoundException(UserNotFoundException e){

        String code = "ERROR_CODE_0001";
        String description = "회원 정보 조회에 실패하셨습니다.";
        String detail = e.getMessage();

        return new ResponseEntity<>(new ErrorResponse(code, description, detail), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e){

        String code = "";
        String description = "";
        String detail = "";



        /* 유효성 검사에서 error 가 발생한다면? */
        if(e.getBindingResult().hasErrors()){        // Binding : 내가 작성한 DTO 클래스와 매핑된다

            detail = e.getBindingResult().getFieldError().getDefaultMessage();      // e.getMessage() 와 동일한 의미

            System.out.println("detail" + detail);

            // NotNull, Size, Blank etc... 에 해당하는 문제가 생겼을 때 bindResultCode 로 넘겨줌
            String bindResultCode = e.getBindingResult().getFieldError().getCode();


            switch (bindResultCode) {
                case "NotBlank" :
                    code = "ERROR_CODE_0002";
                    description = "NotBlank. 필수 값이 누락되었습니다!!!";
                    break;
                case "Size" :
                    code = "ERROR_CODE_0003";
                    description = "size 가 안맞음. 글자수를 확인해주세요";
                    break;
            }
        }
                return new ResponseEntity<>(new ErrorResponse(code, description, detail), HttpStatus.BAD_REQUEST);
    }

}
