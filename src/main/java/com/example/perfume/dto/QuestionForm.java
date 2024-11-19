package com.example.perfume.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
    @NotEmpty(message = "2~200자내 제목을 입력해주세요")
    @Size(min=2, max=200)
    private String subject;
    
    @NotEmpty(message = "내용을 입력해주세요")
    private String content;
}
