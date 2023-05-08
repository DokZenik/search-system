package com.example.testchatbot.service;

import com.example.testchatbot.domen.Degree;
import com.example.testchatbot.domen.Department;
import com.example.testchatbot.dto.CountDegreeDTO;
import com.example.testchatbot.repository.DepartmentLecturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentLectureService {
    private final DepartmentLecturerRepository departmentLecturerRepository;

    public Long averageSalaryByDepartmentName(String departmentName) throws IllegalArgumentException{
        Long averageSalary = departmentLecturerRepository.getAverageSalary(departmentName);
        if(averageSalary == null)
            throw new IllegalArgumentException("Department with name \"" + departmentName + "\" does not exists");
        return averageSalary;
    }

    public Long employeeNumberByDepartment(String departmentName) throws IllegalArgumentException{
        Long size = departmentLecturerRepository.employeesNumber(departmentName);
        if(size == null)
            throw new IllegalArgumentException("Department with name \"" + departmentName + "\" does not exists");
        return size;
    }
    public Map<Degree, Long> countEmployees(String departmentName) throws IllegalArgumentException{
        Map<Degree, Long> buff = new HashMap<>();
        buff.put(Degree.ASSISTANT, 0L);
        buff.put(Degree.ASSOCIATE_PROFESSOR, 0L);
        buff.put(Degree.PROFESSOR, 0L);
        List<CountDegreeDTO> employeesCount = departmentLecturerRepository.getEmployeesCount(departmentName);

        if(employeesCount == null)
            throw new IllegalArgumentException("Department with name \"" + departmentName + "\" does not exists");
        employeesCount.forEach(elem -> buff.put(elem.getDegree(), elem.getCount()));


        return buff;
    }
}
