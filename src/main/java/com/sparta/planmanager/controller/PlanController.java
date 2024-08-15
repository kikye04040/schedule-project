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
        @RequestParam(required = false) Long managerId,
        @RequestParam(required = false) String updateAt) {
    return planService.getAllPlans(managerId, updateAt);
    }

    @PutMapping("/plans/{id}")
    public PlanResponseDto updatePlan(
            @RequestParam Long id,
            @RequestBody PlanRequestDto requestDto) {
        return planService.updatePlan(id, requestDto);
    }

    @DeleteMapping("/plans/{id}")
    public String deletePlan(
            @PathVariable Long id,
            @RequestParam String password) {
        planService.deletePlan(id, password);
        return "일정이 삭제되었습니다.";
    }
}
