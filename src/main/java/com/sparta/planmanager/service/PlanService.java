package com.sparta.planmanager.service;

import com.sparta.planmanager.dto.PlanRequestDto;
import com.sparta.planmanager.dto.PlanResponseDto;
import com.sparta.planmanager.entity.Plan;
import com.sparta.planmanager.exception.IncorrectPasswordException;
import com.sparta.planmanager.exception.PlanNotFoundException;
import com.sparta.planmanager.repository.PlanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<PlanResponseDto> getAllPlans(Long managerId, String updateAt, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Plan> plans = planRepository.findAllByConditions(managerId, updateAt, pageNumber, pageSize);
        return plans.stream().map(PlanResponseDto::new).collect(Collectors.toList());
    }

    public PlanResponseDto updatePlan(Long id, PlanRequestDto planRequestDto) {
        // 1. DB 에서 기존 일정 조회
        Plan plan = planRepository.findById(id);
        if (plan == null) {
            throw new PlanNotFoundException("해당 일정이 존재하지 않습니다.");
        }
        // 2. 비밀번호 일치 여부 확인
        if (!plan.getPassword().equals(planRequestDto.getPassword())) {
            throw new IncorrectPasswordException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 할일, 담당자명 업데이트
        plan.setTodo(planRequestDto.getTodo());
        plan.setManagerId(planRequestDto.getManagerId());

        // 4. 수정일을 현재 시점으로 변경
        plan.setUpdateAt(LocalDateTime.now());

        // 5. 엽데이트한 일정 저장
        planRepository.update(plan);

        return new PlanResponseDto(plan);
    }

    public void deletePlan(Long id, String password) {
        // 1. DB 에서 기존 일정 조회
        Plan plan = planRepository.findById(id);
        if (plan == null) {
            throw new PlanNotFoundException("해당 일정이 존재하지 않습니다.");
        }

        // 2. 비밀번호 일치 여부 확인
        if (!plan.getPassword().equals(password)) {
            throw new IncorrectPasswordException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 일정 삭제
        planRepository.delete(id);
    }
}
