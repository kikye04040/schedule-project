package com.sparta.planmanager.service;

import com.sparta.planmanager.dto.ManagerRequestDto;
import com.sparta.planmanager.dto.ManagerResponseDto;
import com.sparta.planmanager.entity.Manager;
import com.sparta.planmanager.repository.ManagerRepository;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public ManagerResponseDto createManager(ManagerRequestDto requestDto) {
        Manager manager = new Manager(requestDto.getName(), requestDto.getEmail());
        Manager savedManager = managerRepository.save(manager);
        return new ManagerResponseDto(savedManager);
    }

    public ManagerResponseDto getManagerById(Long id) {
        Manager manager = managerRepository.findById(id);
        if (manager == null) {
            throw new IllegalArgumentException("해당 담당자가 존재하지 않습니다.");
        }
        return new ManagerResponseDto(manager);
    }
}
