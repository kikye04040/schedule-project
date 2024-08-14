package com.sparta.planmanager.entity;

import com.sparta.planmanager.dto.PlanRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Plan {
    private Long id;
    private String todo;
    private String managername;
    private String password;
    private String date;

    public Plan(PlanRequestDto requestDto) {
        this.todo = requestDto.getTodo();
        this.managername = requestDto.getManagername();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }
}
