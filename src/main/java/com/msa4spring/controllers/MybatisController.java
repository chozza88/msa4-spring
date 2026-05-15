package com.msa4spring.controllers;


import com.msa4spring.entities.Employee;
import com.msa4spring.requests.EmployeesStoreRequest;
import com.msa4spring.responses.ResponseDTO;
import com.msa4spring.services.MybatisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MybatisController {
    private final MybatisService mybatisService;

    @GetMapping("/employees")
    public ResponseEntity<ResponseDTO<List<Employee>>> index() {
        List<Employee> result = mybatisService.getEmployees();

        ResponseDTO<List<Employee>> responseDTO = ResponseDTO.<List<Employee>>builder()
                .code("00")
                .msg("정상처리")
                .data(result)
                .build();

        return ResponseEntity.status(200).body(responseDTO);
    }

    @PostMapping("/employees")
    public ResponseEntity<ResponseDTO<Employee>> store(
            @Valid @ModelAttribute EmployeesStoreRequest employeesStoreRequest
    ){
        Employee result = mybatisService.store(employeesStoreRequest);

        ResponseDTO<Employee> responseDTO = ResponseDTO.<Employee>builder()
                .code("00")
                .msg("정상처리")
                .data(result)
                .build();


        return ResponseEntity.status(200).body(responseDTO);
    }
}
