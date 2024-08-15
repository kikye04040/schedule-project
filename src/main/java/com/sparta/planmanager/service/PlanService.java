package com.sparta.planmanager.service;

import com.sparta.planmanager.dto.PlanRequestDto;
import com.sparta.planmanager.dto.PlanResponseDto;
import com.sparta.planmanager.entity.Plan;
import com.sparta.planmanager.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<PlanResponseDto> getAllPlans(String managername, String updateAt) {
        List<Plan> plans = planRepository.findAllByConditions(managername, updateAt);
        return plans.stream().map(PlanResponseDto::new).collect(Collectors.toList());
    }
}
