package com.sparta.planmanager.entity;

import com.sparta.planmanager.dto.PlanRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Plan {
    private Long id;
    private String todo;
    private Long managerId;
    private String password;
    private String date;
    private LocalDateTime createAt; // 작성일
    private LocalDateTime updateAt; // 수정일

    public Plan(String todo, Long managerId, String password, String date) {
        this.todo = todo;
        this.managerId = managerId;
        this.password = password;
        this.date = date;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
}
