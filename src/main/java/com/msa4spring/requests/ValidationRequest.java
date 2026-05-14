package com.msa4spring.requests;

import jakarta.validation.constraints.*;

public record ValidationRequest(
        // 널인지 아닌지, 빈문자열인지 아닌지 확인
        @NotBlank (message = "이메일 필수")
        String email,

        @NotBlank(message = "비밀번호 필수")
        @Size(min = 5, max = 20, message = "비밀번호 5~20글자 허용")
        String password,

        // 타입 일치를 위해서 NotNull.을 사용
        @NotNull(message = "나이 필수")
        @Min(0)
        @Max(200)
        Integer age,

        @NotBlank(message = "이름은 필수입니다.")
        @Pattern(regexp = "^[가-힣]{2,50}$", message = "한글 2~50글자 사이")
        String name
) {
}
