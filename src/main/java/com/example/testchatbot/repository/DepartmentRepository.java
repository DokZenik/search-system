package com.example.testchatbot.repository;

import com.example.testchatbot.domen.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentByName(String departmentName);

    List<Department> findAllByNameContaining(String regex);
}
