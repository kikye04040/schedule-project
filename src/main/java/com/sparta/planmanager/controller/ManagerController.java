package com.sparta.planmanager.controller;

import com.sparta.planmanager.dto.ManagerRequestDto;
import com.sparta.planmanager.dto.ManagerResponseDto;
import com.sparta.planmanager.service.ManagerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping
    public ManagerResponseDto createManager(@RequestBody ManagerRequestDto requsetDto) {
        return managerService.createManager(requsetDto);
    }

    @GetMapping("/{id}")
    public ManagerResponseDto getManagerById(@PathVariable Long id) {
        return managerService.getManagerById(id);
    }
}
