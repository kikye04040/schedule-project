package com.sparta.planmanager.dto;

import com.sparta.planmanager.entity.Plan;
import lombok.Getter;

@Getter
public class PlanResponseDto {
    private Long id;
    private String todo;
    private String managername;
    private String password;
    private String date;
    private String createAt;
    private String updateAt;

    public PlanResponseDto(Plan plan) {
        this.id = plan.getId();
        this.todo = plan.getTodo();
        this.managername = plan.getManagername();
        this.password = plan.getPassword();
        this.date = plan.getDate();
        this.createAt = plan.getCreateAt().toString(); // 작성일
        this.updateAt = plan.getUpdateAt().toString();
    }

    public PlanResponseDto(Long id, String todo, String managername, String password, String date, String createAt, String updateAt) {
        this.id = id;
        this.todo = todo;
        this.managername = managername;
        this.password = password;
        this.date = date;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
