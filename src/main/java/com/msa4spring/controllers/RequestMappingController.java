package com.msa4spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController: RestApi Controller로 선언하기 위한 어노테이션
//@RequestMapping: 클래스 레벨의 맵핑 (그루핑 있는 request 매핑)
@RestController
@RequestMapping("/api")
public class RequestMappingController {
    @GetMapping("/test")
    public String test() {
        return "GET 테스트, http://localhost:8080/test";
    }

    @PostMapping("/test")
    public String testPost(){
        return "POST 테스트";
    }

}
