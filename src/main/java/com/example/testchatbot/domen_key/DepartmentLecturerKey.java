package com.example.testchatbot.domen_key;

import com.example.testchatbot.domen.Department;
import com.example.testchatbot.domen.Lecturer;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class DepartmentLecturerKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;
}
