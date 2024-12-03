package com.example.portfolio.__18.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFindDto {
    @NotNull(message = "이메일을 입력해주세요")
    @Email
    private String email;

    private String name;


}
