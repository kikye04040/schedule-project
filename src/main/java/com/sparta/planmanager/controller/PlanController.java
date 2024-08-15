package com.sparta.planmanager.controller;

import com.sparta.planmanager.dto.ErrorResponseDto;
import com.sparta.planmanager.dto.PlanRequestDto;
import com.sparta.planmanager.dto.PlanResponseDto;
import com.sparta.planmanager.service.PlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<Object> createPlan(@Valid @RequestBody PlanRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(new ErrorResponseDto(errorMessage));
        }
        PlanResponseDto responseDto = planService.createPlan(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/plans/{id}")
    public ResponseEntity<Object> updatePlan(@PathVariable Long id, @Valid @RequestBody PlanRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(new ErrorResponseDto(errorMessage));
        }
        PlanResponseDto responseDto = planService.updatePlan(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/plans/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable Long id, @RequestParam String password) {
        planService.deletePlan(id, password);
        return new ResponseEntity<>("일정이 삭제되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/plans")
    public ResponseEntity<List<PlanResponseDto>> getAllPlans(@RequestParam(required = false) Long managerId,
                                                             @RequestParam(required = false) String updateAt,
                                                             @RequestParam(defaultValue = "0") int pageNumber,
                                                             @RequestParam(defaultValue = "10") int pageSize) {
        List<PlanResponseDto> plans = planService.getAllPlans(managerId, updateAt, pageNumber, pageSize);
        return ResponseEntity.ok(plans);
    }
}
