package com.msa4spring.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 로깅을 위한 어노테이션을 클래스레벨에 추가
@Slf4j
@RestController
@RequestMapping("/api")
public class LoggingController {
    @GetMapping("/log")
    public String logging(){
        log.trace("트레이스 레벨");
        log.debug("디버그 레벨");
        log.info("인포 레벨");
        log.warn(("워닝 레벨"));
        log.error("에러 레벨");


        return "GET logging";
    }
}
