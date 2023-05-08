package com.example.testchatbot.dto;

import com.example.testchatbot.domen.Degree;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountDegreeDTO {
    private Degree degree;
    private Long count;
}
