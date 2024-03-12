package com.example.encore_spring_pjt.ctrl.valid.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserRequestDTO {
    private String name;
    private String password;
    @Email
    private String email;
    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}", message = "전화번호 입력양식을 확인해주세요")
    private String cellPhone;
    @Min(value = 0, message = "유효한 나이를 입력해 주세요")
    @Max(value = 150, message = "유효한 나이를 입력해 주세요")
    private int age;
}
