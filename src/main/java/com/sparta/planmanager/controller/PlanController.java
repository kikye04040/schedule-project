package com.sparta.planmanager.controller;

import com.sparta.planmanager.dto.PlanRequestDto;
import com.sparta.planmanager.dto.PlanResponseDto;
import com.sparta.planmanager.service.PlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping("/plans")
    public PlanResponseDto createPlan(@RequestBody PlanRequestDto requestDto) {
        return planService.createPlan(requestDto);
    }

    @GetMapping("/plans")
    public List<PlanResponseDto> getAllPlans(
        @RequestParam(required = false) String managername,
        @RequestParam(required = false) String updateAt) {
    return planService.getAllPlans(managername, updateAt);
    }
}
