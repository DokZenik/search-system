package com.example.testchatbot.service;

import com.example.testchatbot.domen.Department;
import com.example.testchatbot.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public String getHeadByName(String departmentName) throws IllegalArgumentException{
        Department department = departmentRepository.findDepartmentByName(departmentName);

        if(department == null)
            throw new IllegalArgumentException("Department with name \"" + departmentName + "\" does not exists");

        return department.getLecturer().toString();
    }

    public List<String> globalSearch(String regex){
        return departmentRepository.findAllByNameContainingIgnoreCase(regex)
                .stream()
                .map(Department::getName)
                .collect(Collectors.toList());
    }
}
