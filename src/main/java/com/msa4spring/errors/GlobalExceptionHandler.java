package com.msa4spring.errors;

import com.msa4spring.responses.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;

// ExceptionHandler 클래스 생성(클래스명은 아무거나 상관없다.)
// 에러가 나면 이 클래스 파일에서 찍어볼 수 있기 때문에 @Slf4j가 필요함.
// @RestControllerAdvice가 있어야 글로벌 Exception Handler로 만들 수 있음.
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 예외 처리를 실행할 메소드 정의(메소드명 자유)

    // 유효성 검사 실패 예외 처리 (@Valid, @Validated)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<List<String>>> validationHandle(MethodArgumentNotValidException e){
        List<String> errorMsgList = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList();

        ResponseDTO<List<String>> responseDTO = ResponseDTO.<List<String>>builder()
                .code("E01")
                .msg("유효성 검사 실패")
                .data(errorMsgList)
                .build();
        return ResponseEntity.status(400).body(responseDTO);
    }

    // DB 관련 예외 핸들러
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ResponseDTO<String>> sqlExceptionHandle(SQLException e){
        log.error(e.getMessage());

        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder()
                .code("E80")
                .msg("DB 에러 발생")
                .data("현재 서비스 이용 불가합니다.\n잠시후 다시 시도해 주십시오.")
                .build();
        return ResponseEntity.status(500).body(responseDTO);
    }



    // 실무에서는 Response객체를 여러개 만듦(정상, 오류 등등...)
    // Exception.class는 Exception 클래스의 타입 정보를 의미
    // 해당 타입의 예외가 발생하면 이 메서드가 실행됨
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> othersHandle(Exception e){
        log.error(e.getMessage());

        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder()
                .code("E99")
                .msg("서버 에러 발생")
                .data("현재 서비스 이용 불가합니다.\n잠시후 다시 시도해 주십시오.")
                .build();
        return ResponseEntity.status(500).body(responseDTO);
    }
}
