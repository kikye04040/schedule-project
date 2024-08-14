package com.sparta.planmanager.entity;

import com.sparta.planmanager.dto.PlanRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Plan {
    private Long id;
    private String todo;
    private String managername;
    private String password;
    private String date;
    private LocalDateTime createAt; // 작성일
    private LocalDateTime updateAt; // 수정일

    public Plan(PlanRequestDto requestDto) { // plan 객체 생성 시, 작성일과 수정일을 현재 시간으로 설정
        this.todo = requestDto.getTodo();
        this.managername = requestDto.getManagername();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
        this.createAt = LocalDateTime.now(); // 현재 시간을 작성일로 설정
        this.updateAt = this.createAt; // 수정일도 처음엔 작성일과 동일하게 설정
    }
}
