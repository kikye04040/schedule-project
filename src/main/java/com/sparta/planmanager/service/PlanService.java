package com.sparta.planmanager.service;

import com.sparta.planmanager.dto.PlanRequestDto;
import com.sparta.planmanager.dto.PlanResponseDto;
import com.sparta.planmanager.entity.Plan;
import com.sparta.planmanager.repository.PlanRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanService {
    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public PlanResponseDto createPlan(PlanRequestDto planRequestDto) {
        Plan plan = new Plan();

        Plan savePlan = planRepository.save(plan);

        PlanResponseDto planResponseDto = new PlanResponseDto(plan);

        return planResponseDto;
    }
}
