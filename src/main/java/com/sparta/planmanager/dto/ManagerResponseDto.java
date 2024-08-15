package com.sparta.planmanager.dto;

import com.sparta.planmanager.entity.Manager;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ManagerResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public ManagerResponseDto(Manager manager) {
        this.id = manager.getId();
        this.name = manager.getName();
        this.email = manager.getEmail();
        this.createAt = manager.getCreateAt();
        this.updateAt = manager.getUpdateAt();
    }
}
