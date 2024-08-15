package com.sparta.planmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PlanRequestDto {
    @NotBlank(message = "할일은 필수 입력 항목입니다.")
    @Size(max = 200, message = "할일은 최대 200자까지 입력 가능합니다.")
    private String todo;

    @NotBlank(message = "담당자 ID는 필수 입력 항목입니다.")
    private Long managerId;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    private String password;

    private String date;
}
