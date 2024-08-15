package com.sparta.planmanager.dto;

import lombok.Getter;

@Getter
public class PlanRequestDto {
    private String todo;
    private Long managerId;
    private String password;
    private String date;
}
