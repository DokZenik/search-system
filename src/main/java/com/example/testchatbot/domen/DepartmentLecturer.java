package com.example.testchatbot.domen;

import com.example.testchatbot.domen_key.DepartmentLecturerKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "department_lecturer")
public class DepartmentLecturer {
    @EmbeddedId
    private DepartmentLecturerKey departmentLecturerKey;

    @Column(nullable = false)
    private Long salary;
}
