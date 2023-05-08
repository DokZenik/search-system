package com.example.testchatbot.repository;

import com.example.testchatbot.domen.Degree;
import com.example.testchatbot.domen.Department;
import com.example.testchatbot.domen.DepartmentLecturer;
import com.example.testchatbot.domen_key.DepartmentLecturerKey;
import com.example.testchatbot.dto.CountDegreeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface DepartmentLecturerRepository extends JpaRepository<DepartmentLecturer, DepartmentLecturerKey> {
    @Query("select AVG(dl.salary) from DepartmentLecturer dl where dl.departmentLecturerKey.department.name = ?1")
    Long getAverageSalary(String departmentName);


    @Query("select count(dl) from DepartmentLecturer dl where dl.departmentLecturerKey.department.name = ?1")
    Long employeesNumber(String departmentName);

    @Query("select new com.example.testchatbot.dto.CountDegreeDTO(dl.departmentLecturerKey.lecturer.degree,  count(dl.departmentLecturerKey.lecturer.degree)) from  DepartmentLecturer dl where dl.departmentLecturerKey.department.name = ?1 group by dl.departmentLecturerKey.lecturer.degree")
    List<CountDegreeDTO> getEmployeesCount(String departmentName);
}
