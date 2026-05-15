package com.msa4spring.requests;

import jakarta.validation.constraints.NotBlank;

public record EmployeesStoreRequest(
        @NotBlank(message = "이름 필수")
        String name,

        @NotBlank(message = "생일 필수")
        String birth,

        @NotBlank(message = "성별 필수")
        String gender
) {
}
