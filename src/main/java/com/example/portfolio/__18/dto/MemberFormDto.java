package com.example.portfolio.__18.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class MemberFormDto {
    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name; //이름
    @NotEmpty(message = "아이디는 필수입력값입니다.")
    @Length(min = 5,max = 10,message = "5~10자 사이로 입력해주세요")
    private String username; //유저아이디
    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;
    @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
    @Length(min = 3, max = 16, message = "3~16자 사이로 입력해주세요")
    private String password1;
    @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
    @Length(min = 3, max = 16, message = "3~16자 사이로 입력해주세요")
    private String password2;
    @NotEmpty(message = "주소는 필수 입력값입니다")
    private String address;

}
