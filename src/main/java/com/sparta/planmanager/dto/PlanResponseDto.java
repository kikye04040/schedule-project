package com.sparta.planmanager.dto;

import com.sparta.planmanager.entity.Plan;

public class PlanResponseDto {
    private Long id;
    private String todo;
    private String managername;
    private String password;
    private String date;

    public PlanResponseDto(Plan plan) {
        this.id = plan.getId();
        this.todo = plan.getTodo();
        this.managername = plan.getManagername();
        this.password = plan.getPassword();
        this.date = plan.getDate();
    }

    public PlanResponseDto(Long id, String todo, String managername, String password, String date) {
        this.id = id;
        this.todo = todo;
        this.managername = managername;
        this.password = password;
        this.date = date;
    }
}
