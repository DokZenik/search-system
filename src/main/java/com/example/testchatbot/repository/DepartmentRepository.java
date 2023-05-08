package com.example.testchatbot.repository;

import com.example.testchatbot.domen.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentByName(String departmentName);

    List<Department> findAllByNameContainingIgnoreCase(String regex);
}
